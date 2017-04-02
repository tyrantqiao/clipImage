package qiaoClip;

import java.awt.AWTException;

import qiaoClip.PSMethod;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//import javax.media.CannotRealizeException;
//import javax.media.NoPlayerException;
//import javax.media.Player;
import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.filechooser.FileFilter;

import qiaoClip.ScreenShotWindow;
public class AlarmFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PSMethod psMethod;

	ScreenShotWindow shotWindow;
	public AlarmFrame(){
		Timer timer=new Timer();
		
		JPanel northPanel=new JPanel();
		JPanel southPanel=new JPanel();
		
		JTextField setTimeField=new JTextField(10);
		JTextArea tickArea=new JTextArea();
		JButton runButton=new JButton("run");
		JButton clipButton=new JButton("clip");
		JButton setButton=new JButton("set");
//		JButton musicButton=new JButton("music");
		JLabel timeLabel=new JLabel("input time");
		
		northPanel.add(tickArea);
		southPanel.add(timeLabel);
		southPanel.add(setTimeField);
		southPanel.add(setButton);
		southPanel.add(runButton);
		southPanel.add(clipButton);
//		southPanel.add(musicButton);
		
		add(northPanel,BorderLayout.NORTH);
		add(southPanel,BorderLayout.SOUTH);
		
		tickArea.setFont(new Font("ºÚÌå",Font.BOLD,60));
		tickArea.setEditable(false);
		
		try {
			psMethod=new PSMethod();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String time=setTimeField.getText();
				tickArea.setText(time);
			}
		});
		
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				long time=Long.parseLong(setTimeField.getText())*1000;
				long start=System.currentTimeMillis();
				long end=time+start;
				
//				System.out.println(time);
//				System.out.println(start);
//				System.out.println(end);
				
				timer.schedule(new TimerTask(){
					public void run() {
						long temp=System.currentTimeMillis();
						int tickingTime=(int) ((end-temp)/1000);
						tickArea.setText(Integer.toString(tickingTime));
					}
				},0,1000);
				timer.schedule(new TimerTask(){
					public void run() {
						timer.cancel();
						tickArea.setText(Integer.toString((int)(time/1000)));
					}
				}, new Date(end));
			}
		});
		
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
		
		//TODO jmf or sth to support music 
		/*musicButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				File selectedFile=null;
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\CloudMusic"));
				fileChooser.setFileFilter(new FileFilter(){
					public boolean accept(File f) {
						if(f.isFile()&&f.getName().endsWith(".wma")){
							return true;
						}
						return false;
					}
					public String getDescription() {
						return "mp3 files(from wangyi)";
					}
				});
				int i=fileChooser.showOpenDialog(musicButton);
				if(i==fileChooser.APPROVE_OPTION){
					selectedFile=fileChooser.getSelectedFile();
				}	
				try {
					JMFPlayer player=new JMFPlayer(selectedFile);
					player.play();
				} catch (NoPlayerException | CannotRealizeException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		*/
		pack();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				AlarmFrame frame =new AlarmFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 			}
			
		});
	}

}

