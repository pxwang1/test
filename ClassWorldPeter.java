import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*; 
import java.awt.Color;

/**
 * Write a description of class ClassWorldPeter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClassWorldPeter extends World
{
    //objects
    Counter counter = new Counter("Score: ");

    MouseInfo myMouseInfo;
    ClassBallPeter objClassBallPeterOrig;

    List<ClassBallPeter> listBallsExtra = new ArrayList<ClassBallPeter>();
    java.util.List listBallsAll = null;
    java.util.List listBallsHit = null;

    //variables
    int nMaxBall = 5;
    int nTotalScore = 0;

    int nHighScore = 20;
    int nLowScore = 10;
    int nDeductScore = 5;

    /**
     * Constructor for objects of class ClassWorldPeter.
     * 
     */
    public ClassWorldPeter()
    {    
        // Create a new world with Width x Height cells with a cell size of 1x1 pixels.
        super(900, 500, 1); 

        // Make sure actors are painted in the correct order.
        setPaintOrder(ScoreBoard.class, ClassBallPeter.class, Counter.class);

        GreenfootImage imageBackground = getBackground();
        imageBackground.setColor(Color.BLACK);
        imageBackground.fill();
        
        //populate with initial actor objects
        prepare();
    }

    /**
     * create an ojbect once mouse clicks
     */
    public void act()
    {

        if ( Greenfoot.mouseClicked(null) )
        {
            myMouseInfo = Greenfoot.getMouseInfo();
            if ( !getObjectsAt(myMouseInfo.getX(), myMouseInfo.getY(), ClassBallPeter.class).isEmpty() )
            {
                listBallsHit = getObjectsAt(myMouseInfo.getX(), myMouseInfo.getY(), ClassBallPeter.class);
            }
            //listBallsHit = getObjectsAt(myMouseInfo.getX(), myMouseInfo.getY(), ClassBallPeter.class);
            //listBallsAll = getObjects(ClassBallPeter.class);

            //if (Greenfoot.mouseClicked(objClassBallPeterOrig))
            if ( listBallsHit != null && (listBallsHit.isEmpty()) == false )
            {
                //bingo, we are hitting the correct one!
                //get all objects of class type ClassBallPeter
                listBallsAll = getObjects(ClassBallPeter.class);
                //find out how many elements in the list/array
                int nTotalBallsInList = listBallsAll.size();

                if ( listBallsHit.get(0) == objClassBallPeterOrig )
                {
                    //yes, we hit the orig ball
                    if ( nTotalBallsInList > 1 )
                    {
                        //there are more than 1 balls alive, including the current one
                        Greenfoot.playSound("buzz.wav");

                    } else
                    {
                        // yes, this oBallOrig is the only ball alive
                        Greenfoot.playSound("explosion.wav");
                        objClassBallPeterOrig.setImage("explosion.png");

                        //wonderful, we are all done gracefully
                    }

                    removeObjects(getObjects(ClassBallPeter.class));// and remove all balls on the screen
                    // add score 
                    nTotalScore = nTotalScore + 20;
                    counter.updateImage(nTotalScore);
                    gameOver();
                } else {
                    //hit a ball, but not the original one
                    //listBallsHit = getObjectsAt(myMouseInfo.getX(), myMouseInfo.getY(), ClassBallPeter.class);

                    //is this ball an extra one or added later by mistake?
                    if (listBallsExtra!= null && (listBallsExtra.isEmpty() || !( listBallsExtra.contains(listBallsHit.get(0)) ) ))
                    {
                        Greenfoot.playSound("explosion.wav");
                        nTotalScore = nTotalScore + 20;
                        counter.updateImage(nTotalScore);
                    } 
                    else
                    {
                        //otherwise, this is extra ball, no score will be added
                    }
                    removeObject((ClassBallPeter)listBallsHit.get(0));
                }
            }
            else
            {
                //click on other places, or we did not hit any ball at all
                //oops, sorry, we have to create a new ball
                int nRotation, nDirection;
                nRotation = Greenfoot.getRandomNumber(360);
                nDirection = Greenfoot.getRandomNumber(1000) % 5;

                ClassBallPeter objClassBallPeter = new ClassBallPeter(nRotation, nDirection);
                addObject(objClassBallPeter, myMouseInfo.getX(), myMouseInfo.getY());
                Greenfoot.playSound("pop.wav");
                nTotalScore = nTotalScore - 5;
                counter.updateImage(nTotalScore);

                //push these additional balls into extra ball list, so that when removing them later
                //no points will be added
                listBallsExtra.add(objClassBallPeter);
            }
        }
        listBallsHit = null;
    }

    private void gameOver()
    {

        addObject(new ScoreBoard(nTotalScore), getWidth()/2, getHeight()/2);

        Greenfoot.stop();
    }

    public void countPop()
    {
        counter.add(20);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {

        int nRotation, nImageNum;
        nRotation = Greenfoot.getRandomNumber(360);
        nImageNum = 0; 
        objClassBallPeterOrig = new ClassBallPeter(nRotation, nImageNum);
        int x = Greenfoot.getRandomNumber(getWidth())+20;
        int y = Greenfoot.getRandomNumber(getHeight())+15;
        addObject(objClassBallPeterOrig, x, y);
        objClassBallPeterOrig.MoveABit();

        int nNumOfBallsCreated = 0;
        while (nNumOfBallsCreated < nMaxBall)
        {
            nRotation = Greenfoot.getRandomNumber(360);
            nImageNum = Greenfoot.getRandomNumber(1000) % 4 + 1;
            addObject(new ClassBallPeter(nRotation, nImageNum), Greenfoot.getRandomNumber(580)+20, Greenfoot.getRandomNumber(380)+15);

            nNumOfBallsCreated++;
        }

        addObject(counter, 100, 30);
    }
}
