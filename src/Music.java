import javax.sound.sampled.*;

import java.io.*;

public class Music implements Runnable{
	
	Music(){
		
	}
	
	public void run(){
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(new File("resources/music.wav"));
			AudioFormat af = as.getFormat();
			Clip clip = AudioSystem.getClip();
			DataLine.Info info = new DataLine.Info(Clip.class, af);
			Line line1 = AudioSystem.getLine(info);

            if ( ! line1.isOpen() )
            {
             clip.open(as);
             clip.loop(Clip.LOOP_CONTINUOUSLY);
             clip.start();
            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Could not load Music file!");
		}
	}
}
