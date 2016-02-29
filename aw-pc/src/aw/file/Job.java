package aw.file;

import aw.file.interfaces.JobInterface;

public class Job implements JobInterface {

	private String[] items;
	private int[] quantity;
	private int JobID;
	private int counter;

	public Job(String row) {
		JobID = Integer.parseInt(row.split(",")[0]);
		counter = 0;
		int i = 0;
		int k = 1;

		while (i < row.length()) {
			if (row.charAt(i) == ',') {
				counter++;
			}
			i++;
		}

		items = new String[numberItems()];
		quantity = new int[numberItems()];

		for (int j = 0; j < numberItems(); j++) {
			items[j] = row.split(",")[k];
			int q = Integer.parseInt(row.split(",")[k + 1]);
			quantity[j] = q;
			k = k + 2;
		}

	}

	public String getItem(int index) {
		return items[index];
	}

	public int getQuantity(int index) {
		return quantity[index];
	}

	public int numberItems() {
		return counter / 2;
	}

	public int getID() {
		return JobID;
	}

	@Override
	public ItemList getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getUtility() {
//
//		double totalReward = 0;
//
//		for (int i = 0; i < numberItems(); i++) {
//			ItemList item = new ItemList(items[i]);
//			int j = item.getIndex(items[i]);
//
//			double utility = item.getReward(j) * quantity[i];
//			totalReward += utility;
//
//		}
//		return totalReward;
		return 0;
	}

}
