
package com.amneziac.galaga.objects;

//import com.amneziac.galaga.objects.ammo.RedLaser;
import com.amneziac.galaga.fleets.BugFleet;
import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.io.ImageLoader;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bug1 extends Bug {
    
    public static final Dimension DEFAULT_SIZE = new Dimension (50, 60);
    
    public Bug1(Point startingLocation, BugFleet parent)
    {        
        super(startingLocation, parent, DEFAULT_SIZE);
        
        imagePath = "Bug1Invert.gif";
        speed = 10;

        image = ImageLoader.load(imagePath);

        image = image.getScaledInstance(width, height, 1);
    }
    
//    @Override
//    public Point getGun()
//    {
//        return new Point(x + width/2, y + height);
//    }
//    
}
