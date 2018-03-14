package Tools;
import sun.audio.AudioStream;


import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Soundclip extends JFrame{

    public Soundclip(String fileName){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Testing sound");
        this.setSize(300, 200);
        this.setVisible(true);

        try{
            File soundFile = new File(fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
