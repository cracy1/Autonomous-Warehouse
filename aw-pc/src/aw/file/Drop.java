package aw.file;

import java.io.IOException;

import aw.file.interfaces.DropInterface;

public class Drop implements DropInterface {

	String path = "/Users/Andrei/Documents/workspace/aw/Autonomous-Warehouse/aw-pc/res/";
	String drops = path + "drops.csv";

	private String[] drop;
	private int[] x;
	private int[] y;

	private Drop(){
	
		x= new int[drop.length];
		y =new int[drop.length];
		
	try{
		ReadFile file1 = new ReadFile(drops);
		drop = file1.OpenFile();
	} catch(IOException e){
		System.out.println(e.getMessage());
	}
for(int i = 0; i < drop.length; i++){
	
	x[i] = Integer.parseInt(drop[i].split(",")[0]);
	y[i] = Integer.parseInt(drop[i].split(",")[1]);
}
	}
	
	public int getX(int index){
		return x[index];
	}
	
	public int getY(int index){
		return y[index];
	}
	
}

