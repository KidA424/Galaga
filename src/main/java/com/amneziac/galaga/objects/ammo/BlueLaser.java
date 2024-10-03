
package com.amneziac.galaga.objects.ammo;

import com.amneziac.galaga.io.AudioPlayer;
import com.amneziac.galaga.galaga.Game;
import com.amneziac.galaga.io.ImageLoader;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class BlueLaser extends Ammo {
    
//    public static final Dimension DEFAULT_SIZE = new Dimension (8, 20);
    
    private static AudioPlayer audio;
    private static final String audioURL = "trprsht2.wav";
    
    public BlueLaser (Point startingLocation, int velocityRatio, ArrayList<Ammo> array)
    {
        super(startingLocation, velocityRatio, array);
        
        audio.play();
        
        width = DEFAULT_SIZE.width;
        height = DEFAULT_SIZE.height;
        
        imagePath = "BlueLaser.gif";
        image = ImageLoader.load(imagePath);
        image = image.getScaledInstance(width, height, 1);
    }

    public static void initializeAudio()
    {
        audio = AudioPlayer.newUnchecked(audioURL);
    }
}