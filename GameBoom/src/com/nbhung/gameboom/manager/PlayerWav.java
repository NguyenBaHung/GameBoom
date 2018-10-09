package com.nbhung.gameboom.manager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PlayerWav {
    private Clip clip;

    public PlayerWav(String filename) {
        try {
            URL url = getClass().getResource("/res/raw/" + filename + ".wav");
            clip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(url);
            clip.open(input);
        } catch (LineUnavailableException
                | UnsupportedAudioFileException
                | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isOpen() && !clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
    }

    public void loop(int count) {
        clip.loop(count);
    }


}