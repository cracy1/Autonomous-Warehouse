package aw.file;

import java.io.IOException;

import aw.file.interfaces.DropInterface;

public class Drop implements DropInterface {

	private String path = "/home/students/add511/workspace/JobSelection/Autonomous-Warehouse/aw-pc/res/";
	private String drops = path + "drops.csv";

	private int[] x;
	private int[] y;
	private String[] drop;
	private int numberDrops = 0;

	public Drop() {


		try {
			ReadFile file1 = new ReadFile(drops);
			drop = file1.OpenFile();
			numberDrops = file1.countLines();
	}

		 catch (IOException e) {
			System.out.println(e.getMessage());
		}		
		x = new int[numberDrops];
		y = new int[numberDrops];
		for (int i = 0; i < numberDrops; i++) {
			x[i] = Integer.parseInt(drop[i].split(",")[0]);
			y[i] = Integer.parseInt(drop[i].split(",")[1]);
		}
		
	}

	public int getX(int index) {
		return x[index];
	}

	public int getY(int index) {
		return y[index];
	}

	public int numberDrops() {
		return numberDrops;
	}
	
	public void sortClosestDropToRobot(int i, int j){
		int closest = Integer.MAX_VALUE;
		int tempX = 0;
		int tempY = 0;
		for(int k = 0; k < x.length; k++){
			int distance = Math.abs(i - x[i]) + Math.abs(j - y[i]);
			if(closest > distance){
				closest = distance;
				tempX = x[1];
				tempY = x[1];
				x[1] = x[i];
				y[1] = y[i];
				x[i] = tempX;
				y[i] = tempY;
			}
		}
		
	}

}
