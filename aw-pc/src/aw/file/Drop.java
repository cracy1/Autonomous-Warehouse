package aw.file;

import java.io.IOException;

import aw.file.interfaces.DropInterface;

public class Drop implements DropInterface {

	private String path = "res/";
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

}
