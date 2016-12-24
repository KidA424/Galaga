
package objects;

import galaga.Game;
import java.awt.*;


public abstract class VisibleObject extends Rectangle {
    
    public static int speed;
    protected String imagePath;
    protected Image image;
    protected boolean destroyed = false;
    

    public VisibleObject (Point startingLocation)
    {
        super(startingLocation);
    }
    
    public VisibleObject (Point startingLocation, Dimension size)
    {
        super(startingLocation, size);
    }
    
    public int getRightX()
    {
        return (int) (getX() + width);
    }
    
    public int getBottomY()
    {
        return (int) (getY() + height);
    }
        
    public void paint(Graphics g)
    {
        g.drawImage(image, (int)getX(), (int)getY(), null);
    }
    
    public abstract void actionPerformed();
    
    public boolean isDestroyed()
    {
        return destroyed;
    }
    
    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }
    
    public boolean isOnScreen()
    {
        return this.intersects(Game.RECTANGLE);
    }
}
