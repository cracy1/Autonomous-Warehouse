package aw.file;

import java.io.IOException;

import aw.file.interfaces.DropInterface;

public class Drop implements DropInterface {
	private String drops = "res/drops.csv";

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
	/*
	 * Gets the x coordinate of the drop point at the given index.
	 * @param index the index of the drop point
	 * @return the x coordinate of the specific drop point
	 */
	@Override
	public int getX(int index) {
		return x[index];
	}

	/*
	 * Gets the y coordinate of the drop point at the given index.
	 * @param index the index of the drop point
	 * @return the y coordinate of the specific drop point
	 */
	@Override
	public int getY(int index) {
		return y[index];
	}

	/*
	 * Gets the number of drop points on the map.
	 * @return the number of drop points on the map
	 */
	@Override
	public int numberDrops() {
		return numberDrops;
	}
	
	/*
	 * Sorts the drop points in a way that the drop point closest to the robot becomes the one at index 0 in the array.
	 * @param i the x coordinate of the robot's position
	 * @param j the y coordinate of the robot's position
	 */
	@Override
	public void sortClosestDropToRobot(int i, int j){
		int closest = Integer.MAX_VALUE;
		int tempX = 0;
		int tempY = 0;
		for(int k = 0; k < numberDrops(); k++){
			int distance = Math.abs(i - x[k]) + Math.abs(j - y[k]);
			if(closest > distance){
				closest = distance;
				tempX = x[0];
				tempY = y[0];
				x[0] = x[k];
				y[0] = y[k];
				x[k] = tempX;
				y[k] = tempY;
			}
		}
		
	}

}
