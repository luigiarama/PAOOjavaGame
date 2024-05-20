package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class ThemeSong {
    Clip clip;
    URL[] soundUrl = new URL[30];
    public ThemeSong(){

        soundUrl[0] = getClass().getResource("/soundboard/lalune.wav");
        soundUrl[1] = getClass().getResource("/soundboard/menuSong.wav");
        soundUrl[3] = getClass().getResource("/soundboard/BTTF.wav");
        soundUrl[2] = getClass().getResource("/soundboard/coloratura.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
