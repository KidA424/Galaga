
package objects.ammo;

import galaga.Game;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class BlueLaser extends Ammo {
    
//    public static final Dimension DEFAULT_SIZE = new Dimension (8, 20);
    
    private static AudioClip audio;
    private static final String audioURL = "file:" + Game.path + "trprsht2.wav";
    
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
        try
        {
            audio = Applet.newAudioClip(new URL(audioURL));
        } catch (MalformedURLException ex) {}
    }
}