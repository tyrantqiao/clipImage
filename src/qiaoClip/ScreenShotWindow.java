package qiaoClip;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JWindow;

public class ScreenShotWindow extends JWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	private static BufferedImage saveImage;
	private ParentToolsWindow toolsWindow;
	private BufferedImage clipImage;
	private BufferedImage tempImage2;
	private int buffX,buffY;
	private Point start;
	private Point end;
	private Robot robot;
	
	public ScreenShotWindow() throws AWTException{
		this.setBounds(0,0,d.width,d.height);
		
		robot=new Robot();
		clipImage=robot.createScreenCapture(new Rectangle(0,0,d.width,d.height));
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event){
				start=event.getPoint();
			}
			
			public void mouseReleased(MouseEvent event){
				end=event.getPoint();	
				if(toolsWindow!=null){
					toolsWindow.setVisible(true);
				}else{
					toolsWindow=new ParentToolsWindow(ScreenShotWindow.this,(int)end.getX(),(int)end.getY());
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent event) {
				buffX=event.getX();
				buffY=event.getY();
				
				Image tempImage=createImage(ScreenShotWindow.this.getWidth(),ScreenShotWindow.this.getHeight());
				Graphics g=tempImage.getGraphics();
				g.drawImage(tempImage2, 0, 0, null);
				
				int x=(int) Math.min(start.getX(), buffX);
				int y=(int) Math.min(start.getY(), buffY);
				int width=(int) Math.abs(buffX-start.getX())+1;
				int height=(int) Math.abs(buffY-start.getY())+1;
				
				g.setColor(Color.BLUE);
				g.drawRect(x-1, y-1, width+1, height+1);
				
				saveImage=clipImage.getSubimage(x, y, width, height);
				g.drawImage(saveImage, x, y, null);
				
				ScreenShotWindow.this.getGraphics().drawImage(tempImage, 0, 0, ScreenShotWindow.this);
			}
		});
	}
	
	public void paint(Graphics g){
		RescaleOp ro=new RescaleOp(0.8f,0,null); //亮度和对比度调整东东
		tempImage2=ro.filter(clipImage, null);
		g.drawImage(tempImage2, 0, 0, this);
	}
	
	public void savePicture(ScreenShotWindow window){
		
		JFileChooser fileChooser=new JFileChooser(".");
		
		int i=fileChooser.showSaveDialog(window);
		if(i==JFileChooser.APPROVE_OPTION){
			try {
				int k=1;
				int imageNum=checkImageExist(k);
				System.out.println(imageNum);
				ImageIO.write(saveImage, "png", new File(imageNum+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int checkImageExist(int imageNum){
		if(new File("./"+imageNum+".jpg").exists()){
			imageNum++;
			System.out.println("img exists:"+imageNum);
			imageNum=checkImageExist(imageNum);
		}
		return imageNum;
	}
	
	public static void copyPicture(){
		new OCRImageWindow(saveImage).setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
	}

}
