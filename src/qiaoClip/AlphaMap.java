package qiaoClip;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlphaMap {
	
	private static Map<int [][],String> alphaMap=new HashMap<int [][],String>();

	//TODO add RGB[][] and name
	public synchronized void addAlpha(int alpha[][],String name){
		alphaMap.put(alpha,name);
	}
	
	public synchronized void removeAlpha(int alpha[][]){
		alphaMap.remove(alpha);
	}

	public synchronized void replaceAlpha(int alpha[][],String name){
		alphaMap.replace(alpha,name);
	}
	
	public synchronized static boolean isEmpty(){
		return alphaMap.isEmpty();
	}
	
	public synchronized static Map<int [][],String> getMap(){
		return alphaMap;
	}
	
	public   Set<int[][]> getRGBSet(){
		return alphaMap.keySet();
	}
	
	public static boolean isCotanins(int alpha[][]){
		return alphaMap.containsKey(alpha);
	}
	
	public static boolean isCotanins(String name){
		return alphaMap.containsValue(name);
	}
	
	public static int getSize(){
		return alphaMap.size();
	}
	
	

	public static void main(String[] args) {
		
	}

}
