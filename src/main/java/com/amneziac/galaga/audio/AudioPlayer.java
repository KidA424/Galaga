package com.amneziac.galaga.audio;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String filepath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        load(filepath);
    }

    public static AudioPlayer newUnchecked(String filepath) {
        try {
            return new AudioPlayer(filepath);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(String filepath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        URL resourceUrl = AudioPlayer.class.getClassLoader().getResource(filepath);
        if (resourceUrl == null) {
            throw new FileNotFoundException(filepath);
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(resourceUrl);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
