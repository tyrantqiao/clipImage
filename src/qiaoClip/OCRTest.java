package qiaoClip;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;

import qiaoClip.ScreenShotWindow;

public class OCRTest extends JFrame{
	private BufferedImage image;
	private ScreenShotWindow shotWindow;
	
	public OCRTest(){
		JButton clipButton=new JButton("clip image");
		clipButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					shotWindow=new ScreenShotWindow();
					shotWindow.setVisible(true);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}	
			}	
		});
	}
	
	
	public static void main(String[] args) {
		
	}

}
