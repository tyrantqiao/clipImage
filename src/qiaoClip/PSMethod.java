package qiaoClip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class PSMethod {
//	private static BufferedImage image;
	public static AlphaMap alphaMap=new AlphaMap(); 
	
	public PSMethod() throws IOException{
		for(int i=0;i<10;i++){
			alphaMap.addAlpha(getImageRGB(readImage(i)),Integer.toString(i));
		}
		System.out.println(AlphaMap.getSize());
	}
	
	public BufferedImage readImage(int a) throws IOException{
		java.net.URL imgURL = PSMethod.class.getResource("/images/"+a+".png");
		return ImageIO.read(imgURL);
	}
	
	
	public static BufferedImage grayPicture(BufferedImage image){
		BufferedImage zoomImage=new BufferedImage(8,8,BufferedImage.TYPE_INT_RGB);
		zoomImage.getGraphics().drawImage(image.getScaledInstance(8, 8,java.awt.Image.SCALE_SMOOTH),0, 0, null);
		
		for(int x=0;x<zoomImage.getWidth();++x){
			for(int y=0;y<zoomImage.getHeight();++y){
				int oringinRGB=zoomImage.getRGB(x, y);
				Color color=new Color(oringinRGB);
				int grayInt=(color.getRed()*30+color.getBlue()*11+color.getGreen()*59)/100;
				Color grayColor=new Color(grayInt,grayInt,grayInt);
				image.setRGB(x, y, grayColor.getRGB());
			}
		}
		return zoomImage;
	}
	
	
	
	public static String identifyImage(BufferedImage image){
		int i=0,count=0;
		Map<Integer,Integer> resultMap=new HashMap<Integer ,Integer>();  //cannot use the fundamental types "int". 
		System.out.println(AlphaMap.getSize());
		for(int[][] alphaMap:alphaMap.getRGBSet())
		{
			count=0;
			for(int x=0;x<image.getWidth();x++){
				for(int y=0;y<image.getHeight();y++){
					if(image.getRGB(x, y)==alphaMap[x][y])
						count++;
				}
			}
			resultMap.put(i++, count);
			System.out.println("i="+i);
		}
		int result=getBestIdentification(resultMap);
		return Integer.toString(result);
	}
	
	private static int getBestIdentification(Map<Integer,Integer> map) {
		int max=(int) map.get(0);
		int alpha=0;
		for(int i=1;i<10;i++){
			if(max<(int)map.get(i)){
				max=(int)map.get(i);
				alpha=i;
			}
		}
		return alpha;
	}

	public static int[][] getImageRGB(BufferedImage image){
		int imgRGB[][] = new int[image.getWidth()+1][image.getHeight()+1];
		
		for(int x=0;x<image.getWidth();++x){
			for(int y=0;y<image.getHeight();++y){
				imgRGB[x][y]=image.getRGB(x, y);
			}
		}
		return imgRGB;
	}
	
	//TODO white or black
//	public static Boolean isBlack(){
//		
//	}
	
	public static void main(String[] args) {

	}

}
