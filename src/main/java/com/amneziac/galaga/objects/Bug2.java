
package com.amneziac.galaga.objects;

//import com.amneziac.galaga.objects.ammo.RedLaser;
import com.amneziac.galaga.fleets.BugFleet;
import com.amneziac.galaga.galaga.Game;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Bug2 extends Bug {
    
    public static final Dimension DEFAULT_SIZE = new Dimension (70, 70);
    
    public Bug2(Point startingLocation, BugFleet parent)
    {        
        super(startingLocation, parent, DEFAULT_SIZE);
        
        imagePath = Game.path + "Bug2Invert.gif";
        speed = 10;
        
        try
        {
            image = ImageIO.read(new File(imagePath));
        }
        catch (IOException e) {}; 
        
        image = image.getScaledInstance(width, height, 1);
    }
    
//    @Override
//    public Point getGun()
//    {
//        return new Point(x + width/2, y + height);
//    }
//    
}
