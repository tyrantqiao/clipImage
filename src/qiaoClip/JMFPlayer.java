package qiaoClip;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;
//TODO support jmf for some music
public class JMFPlayer {
	private Player audioPlayer = null;
	
	public JMFPlayer(URL url) throws NoPlayerException, CannotRealizeException, IOException{
		audioPlayer=Manager.createPlayer(url);
	}
	
	public JMFPlayer(File file) throws NoPlayerException, CannotRealizeException, MalformedURLException, IOException{
		this(file.toURI().toURL());
	}
	
	public void play(){
		audioPlayer.start();
	}
	
	public void stop(){
		audioPlayer.stop();
		audioPlayer.close();
	}
	
	public Time getMusicTime(){
		return audioPlayer.getMediaTime();
	}
	
	
	public static void main(String[] args) {
		
	}

}
