package aw.file;

import java.io.IOException;

import aw.file.interfaces.ItemInterface;

public class ItemList implements ItemInterface {
	String path = "res/";

	String items = path + "items.csv";
	String locations = path + "locations.csv";

	private String[] name;
	private Double[] reward;
	private Double[] weight;
	private int[] x;
	private int[] y;

	public ItemList() {

		try

		{
			ReadFile file = new ReadFile(items);
			String[] arr1 = file.OpenFile();
			ReadFile file2 = new ReadFile(locations);
			String[] arr3 = file2.OpenFile();
			
			name = new String[file.countLines()];
			reward = new Double[file.countLines()];
			weight = new Double[file.countLines()];
			x = new int[file2.countLines()];
			y = new int[file2.countLines()];

			for (int i = 0; i < arr1.length; i++) {
				name[i] = arr1[i].split(",")[0];
				reward[i] = Double.parseDouble(arr1[i].split(",")[1]);
				weight[i] = Double.parseDouble(arr1[i].split(",")[2]);
			}

			for (int i = 0; i < arr3.length; i++) {
				//x[i] = Integer.parseInt(arr3[i].split(",")[0]);
				//y[i] = Integer.parseInt(arr3[i].split(",")[1]);
				x[i] = 7 - Integer.parseInt(arr3[i].split(",")[1]); //modified for new coordinate system.
				y[i] = 11 - Integer.parseInt(arr3[i].split(",")[0]);
				
				
			}
		}

		catch (IOException e)

		{
			System.out.println(e.getMessage());
		}
	}

	public String getName(int index) {
		return name[index];
	}

	public double getReward(int index) {
		return reward[index];
	}

	public double getWeight(int index) {
		return weight[index];
	}

	public int getX(int index) {
		return x[index];
	}

	public int getY(int index) {
		return y[index];
	}

	public int getIndex(String string) {
		int i = 0;
		while (i < name.length) {
			if (name[i].equals(string))
				break;
			i++;
		}
		return i;
	}
}
