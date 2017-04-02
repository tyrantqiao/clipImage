package qiaoClip;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JWindow;

public class OCRImageWindow extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public OCRImageWindow(BufferedImage image){
		
		this.image=image;
		setSize(image.getWidth()+150,image.getHeight()+150);
		new OCRToolsWindow(OCRImageWindow.this,image,image.getWidth()+150,image.getHeight()+150);
	}
	
	public void paint(Graphics g){	
		g.drawImage(image, 100, 100, this);
	}
	
	public static void main(String[] args) {
		
	}

}
