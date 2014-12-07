import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class ClassBallPeter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClassBallPeter extends Actor
{
    /*
     * my FIELDS
     * */
    private int nImageNum;
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    private int nSpeed = 4;;
    String strImageName;

    /**
     * contructor for objects of class ClassBallPeter
     *
     */
    public ClassBallPeter()
    {
        nImageNum = 0;
        strImageName = "button-" + nImageNum + ".png";
        setImage(strImageName);
        setRotation(Greenfoot.getRandomNumber(360));
    }

    public ClassBallPeter(int nRotationDirection, int nImageNum)
    {
        GreenfootImage img = new GreenfootImage(MAX_WIDTH, MAX_HEIGHT);
        int r = Greenfoot.getRandomNumber(256);
        int g = Greenfoot.getRandomNumber(256);
        int b = Greenfoot.getRandomNumber(256);
        int a = Greenfoot.getRandomNumber(256);
        a= 255;
        img.setColor(new Color(r, g, b, a));
        img.setColor(randomColor());
        int size = Greenfoot.getRandomNumber(MAX_WIDTH);
        img.fillOval(0, 0, MAX_WIDTH, MAX_HEIGHT);
        //setImage(img);
        
        setMyImage();
        setRotation(nRotationDirection);

        nSpeed = Greenfoot.getRandomNumber(5) + 3;

        //setRotation(nRotationDirection);
        //strImageName = "button-" + nImageNum + ".png";
        //setImage(strImageName);

    }
    
    /*
     * set an image with random size and color circle
     */
    private void setMyImage()
    {
        GreenfootImage img = new GreenfootImage(MAX_WIDTH, MAX_HEIGHT);
        img.setColor(randomColor());
        int size = Greenfoot.getRandomNumber(MAX_WIDTH);
        img.fillOval(0, 0, size, size);
        setImage(img);
    }
    
    void changeTranspancy()
    {
        GreenfootImage img = getImage();
        int alpha = img.getTransparency();
        if (alpha > 0) {
            img.setTransparency(alpha -1);
        } else {
            getWorld().removeObject(this);
        }
        
    }
    
    /*
     * create and return a random color
     */

    private Color randomColor()
    {
        int r = Greenfoot.getRandomNumber(256);
        int g = Greenfoot.getRandomNumber(256);
        int b = Greenfoot.getRandomNumber(256);
        int a = Greenfoot.getRandomNumber(256);
        return new Color(r, g, b, a);
    }

    /**
     * Act - do whatever the ClassBallPeter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        changeTranspancy();
        
        move(nSpeed);
        if( atWorldEdge() ){
            turn(Greenfoot.getRandomNumber(360));
            changeImageRandom();
        }
        // Add your action code here.

        if (Greenfoot.mouseClicked(this))
        {
            //myMouseClicked();
        }
    }  

    public boolean atWorldEdge()
    {

        if( getX() < 10 || getX() > getWorld().getWidth() - 15 )
            return true;

        if ( getY() < 10 || getY() > getWorld().getHeight() - 15 )
            return true;
        else 
            return false;
    }

    public void myMouseClicked()
    {
        Greenfoot.playSound("pop.wav");
        getWorld().removeObject(this);

    }

    public boolean bMouseClickedOnMe()
    {
        //yes, you clicked on me;
        if ( Greenfoot.mouseClicked(this) )
            return true;
        else 
            return false;
    }

    public void changeImageRandom()
    {
        nImageNum++;
        if (nImageNum > 4)
            nImageNum = 0;

        strImageName = "button-" + nImageNum + ".png";
        setImage(strImageName);
    }

    public void changeImage(String strImageName)
    {
        setImage(strImageName);
    }

    public void MoveABit()
    {
        move(10);
        int n = 10;
        while (n > 0)
        {
            setRotation(180);
            n--;
        }

    }
}
