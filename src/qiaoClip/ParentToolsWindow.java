package qiaoClip;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JWindow;

import qiaoClip.ScreenShotWindow;

public class ParentToolsWindow extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JToolBar toolBar;
	private JButton saveButton;
	private JButton cancelButton;
	private JButton copyButton;
	private ScreenShotWindow window;
	
	public ParentToolsWindow(ScreenShotWindow window,int x,int y){
		this.window=window;
		this.windowInit();
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
	}
	
	public void windowInit(){	
        ImageIcon saveImg = createIcon("/images/save.png");
        ImageIcon cancelImg = createIcon("/images/Cancel.png");
        ImageIcon copyImg = createIcon("/images/copy.png");
        
        copyButton=new JButton(copyImg);
		saveButton=new JButton(saveImg);
		cancelButton=new JButton(cancelImg);
		toolBar=new JToolBar("tools");
		
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				window.savePicture(window);
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){ 
				window.dispose();
				dispose();
			}
		});
		
		copyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				window.dispose();
				dispose();
				ScreenShotWindow.copyPicture();
			}
		});
		
		toolBar.add(cancelButton);
		toolBar.add(saveButton);
		toolBar.add(copyButton);
		
		this.add(toolBar,BorderLayout.SOUTH);
	}
	
	public static ImageIcon createIcon(String path){
		java.net.URL imgURL = ParentToolsWindow.class.getResource(path);
		ImageIcon imgIcon=new ImageIcon(imgURL);
		return imgIcon;
	}
	
	public static void main(String[] args) {
		
	}

}
