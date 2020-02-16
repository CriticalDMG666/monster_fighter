package monsterfighter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Effects {

    public static synchronized void play_sound(final String path) {//this plays sound when you call it and write the address
        new Thread(new Runnable() {          
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream(path));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void dots_delay(int dots_number)throws InterruptedException{ //Writes a few dots with delays.
        for (int i = 0; i < dots_number; i++) {
            System.out.print(".");
            Thread.sleep(800);
        }
        System.out.println();
    }

    public static void print(String text)throws InterruptedException { //Prints text like a typewriter
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Thread.sleep(25);
        }
        System.out.println();
    }

}
