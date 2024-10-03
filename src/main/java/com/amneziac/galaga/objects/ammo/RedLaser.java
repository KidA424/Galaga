
package com.amneziac.galaga.objects.ammo;

import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.io.ImageLoader;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class RedLaser extends Ammo {
    
//    public static final Dimension DEFAULT_SIZE = new Dimension (8, 20);
    
    public RedLaser (Point startingLocation, int velocityRatio, ArrayList array)
    {
        super(startingLocation, velocityRatio, array);
        
        imagePath = "RedLaser.gif";
        image = ImageLoader.load(imagePath);
        image = image.getScaledInstance(width, height, 1);
    }
    
}
