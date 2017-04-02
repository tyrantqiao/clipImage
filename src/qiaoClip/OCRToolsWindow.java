package qiaoClip;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JWindow;

import qiaoClip.OCRImageWindow;


public class OCRToolsWindow extends JWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OCRImageWindow window;
	private BufferedImage image;
	
	public OCRToolsWindow(OCRImageWindow window,BufferedImage image,int x,int y){
		this.window=window;
		this.image=image;
		this.windowInit();
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
//		this.setSize(300, 150);
	}
	
	public void windowInit(){
		ImageIcon cancelImg = ParentToolsWindow.createIcon("/images/Cancel.png");
		
		JButton grayButton=new JButton("gray");
		JButton cancelButton=new JButton(cancelImg);
		JButton identifyButton=new JButton("identify");
		JButton saveButton=new JButton("save");
		JTextArea resultArea=new JTextArea();
		JToolBar toolsBar=new JToolBar();
		
		toolsBar.add(grayButton);
		toolsBar.add(saveButton);
		toolsBar.add(identifyButton);
		toolsBar.add(cancelButton);
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser=new JFileChooser(".");
				
				int i=fileChooser.showSaveDialog(window);
				if(i==JFileChooser.APPROVE_OPTION){
					try {
						int k=0;
						int imageNum=ScreenShotWindow.checkImageExist(k);
						System.out.println(imageNum);
						ImageIO.write(image, "jpg", new File(imageNum+".jpg"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		identifyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String result=PSMethod.identifyImage(image);
				resultArea.setText(result);
			}
		});
		
		grayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				image=PSMethod.grayPicture(image);
				window.repaint();
			}			
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.dispose();
				dispose();
			}
		});
		
		this.add(toolsBar,BorderLayout.SOUTH);
		this.add(resultArea, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {

	}

}
