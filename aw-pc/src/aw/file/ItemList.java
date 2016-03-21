package aw.file;

import java.io.IOException;

import aw.file.interfaces.ItemInterface;

public class ItemList implements ItemInterface {
	FilesPath pathForFiles = new FilesPath();
	String items = pathForFiles.path + "items.csv";
	String locations = pathForFiles.path + "locations.csv";

	private String[] name;
	private Double[] reward;
	private Double[] weight;
	private int[] x;
	private int[] y;

	/*
	 * The constructor reads the items and locations files and based on them builds arrays that store information about the items.
	 * Each index corresponds to one unique item, and this index is common through all the arrays.
	 */
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
	
	/*
	 * Gets the item at the given index.
	 * @param index the index of the item in the array
	 * @return the item
	 */
	@Override
	public String getName(int index) {
		return name[index];
	}

	/*
	 * Gets the reward of the item at the given index.
	 * @param index the index of the item in the array
	 * @return the the reward of the item
	 */
	@Override
	public double getReward(int index) {
		return reward[index];
	}

	/*
	 * Gets the weight if the item at the given index.
	 * @param index the index of the item in the array
	 * @return the weight of the item
	 */
	@Override
	public double getWeight(int index) {
		return weight[index];
	}

	/*
	 * Gets the x coordinate of the item at the given index.
	 * @param index the index of the item in the array
	 * @return the x coordinate of the item
	 */
	@Override
	public int getX(int index) {
		return x[index];
	}

	/*
	 * Gets the y coordinate of the item at the given index.
	 * @param index the index of the item in the array
	 * @return the y coordinate of the item
	 */
	@Override
	public int getY(int index) {
		return y[index];
	}

	/*
	 * Gets the index of a particular item.
	 * @param item the item whose index should be found
	 * @return the index of the item
	 */
	@Override
	public int getIndex(String item) {
		int i = 0;
		while (i < name.length) {
			if (name[i].equals(item))
				break;
			i++;
		}
		return i;
	}
}
