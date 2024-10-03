
package com.amneziac.galaga.objects.ammo;

import com.amneziac.galaga.audio.AudioPlayer;
import com.amneziac.galaga.galaga.Game;
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
        
        imagePath = Game.path + "BlueLaser.gif";
        
        try
        {
            image = ImageIO.read(new File(imagePath));
        }
        catch (IOException e) {};
        
        image = image.getScaledInstance(width, height, 1);
    }

    public static void initializeAudio()
    {
        audio = AudioPlayer.newUnchecked(audioURL);
    }
}