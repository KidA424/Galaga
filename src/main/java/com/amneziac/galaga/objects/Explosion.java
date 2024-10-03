
package com.amneziac.galaga.objects;

import com.amneziac.galaga.io.AudioPlayer;
import com.amneziac.galaga.fleets.Fleet;
import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.io.ImageLoader;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Explosion extends VisibleObject {
    
    protected Image originalImage;
    private static AudioPlayer audioPlayer;
    private static final String audioURL = "Bomb2.wav";

    protected Fleet parent;
    protected Point center;
    protected double growthRate = 1.2;
    protected double finalSizeRatio = 2.2;
    protected int originalWidth;
    protected int timePassed = 0;
    Timer timer;
    
    public static final int EXPLOSION_DELAY = 4;
    public static final int ds = 1;
    
    
    public Explosion (Fighter fighter, Fleet parent)
    {
        super(fighter.getLocation(), new Dimension((int)(fighter.getWidth()/2), (int)(fighter.getHeight()/2)));
        this.parent = parent;

        audioPlayer.play();
        
        center = new Point(x + width/2, y + height/2);
        
        if (width >= height)
            height = width;
        else
            width = height;
        
        originalWidth = width;

        imagePath = "Explosion.gif";
        originalImage = ImageLoader.load(imagePath);
        image = originalImage.getScaledInstance(width, height, 1);
        
    }
    
    public void center()
    {
        x = center.x - width/2;
        y = center.y - width/2;
    }
    
    @Override
    public void setLocation(Point location){}

    @Override
    public void actionPerformed()
    {
        if (timePassed >= EXPLOSION_DELAY)
        {
            if(width < originalWidth*finalSizeRatio)
            {
                width = (int) (width*growthRate);
                height = width;
                image = originalImage.getScaledInstance(width, height, 1);
                center();
            }

            else
                destroyed = true;
            
            timePassed = 0;
        }
        
        timePassed++;
    }

    public static void initializeAudio()
    {
        audioPlayer = AudioPlayer.newUnchecked(audioURL);
    }
}
