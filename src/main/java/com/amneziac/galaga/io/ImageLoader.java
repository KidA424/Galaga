package com.amneziac.galaga.io;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static Image load(String imagePath) {
        URL resourceUrl = AudioPlayer.class.getClassLoader().getResource(imagePath);
        if (resourceUrl == null) {
            throw new RuntimeException("Image not found: " + imagePath);
        }
        try {
            return ImageIO.read(resourceUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
