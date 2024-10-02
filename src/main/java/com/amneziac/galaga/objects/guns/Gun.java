package objects.guns;

import java.awt.Point;
import java.util.ArrayList;
import objects.Fighter;
import objects.ammo.Ammo;
import objects.ammo.BlueLaser;
import objects.ammo.RedLaser;

public class Gun {
    
    private Fighter parent;
    private Point relativePosition;
    private Class ammoType;
    private int ammoVelocity;
    private boolean canFire;
    
    public Gun (Point relativePosition, int ammoVelocity, Class ammoType, Fighter parent)
    {
        this.relativePosition = relativePosition;
        this.ammoType = ammoType;
        this.parent = parent;
        this.ammoVelocity = ammoVelocity;
        canFire = true;
    }
    
    public Point getLocation()
    {
        return new Point (getX(), getY());
    }
    
    public int getX()
    {
        return (int) (parent.getX() + relativePosition.x);
    }
    
    public int getY()
    {
        return (int) (parent.getY() + relativePosition.y);
    }
    
    public void setAmmoType(Class ammoType)
    {
        this.ammoType = ammoType;
    }
    
    public void setCanFire(boolean fire)
    {
        canFire = fire;
    }
    
    public void fire()
    {
        if (canFire)
        {
            ArrayList<Ammo> array = parent.getAmmo();

            if (ammoType.equals(BlueLaser.class))
            {
                array.add(new BlueLaser(getLocation(), ammoVelocity, array));
            }

            if (ammoType.equals(RedLaser.class))
            {
                array.add(new RedLaser(getLocation(), ammoVelocity, array));
            }
            
            canFire = false;
        }
    }
}
