package aw.file;

import aw.file.interfaces.JobInterface;

public class Job implements JobInterface {

	private String[] items;
	private int[] quantity;
	private int JobID;
	private int counter;
	private ItemList item = new ItemList();

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
		
		//sort();

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
	public void sort() {		for(int i = 0; i < items.length - 1; i++){
			if(getItemReward(i) < getItemReward(i+1)){
				String x = items[i];
				items[i] = items[i+1];
				items[i+1] = x;
				
				int y = quantity[i];
				quantity[i] = quantity[i+1];
				quantity[i+1] = y;
			}
		}

	}
	@Override
	public double getItemReward(int index) {
		int j = item.getIndex(items[index]);
		double reward = item.getReward(j) * quantity[index];
		return reward;
	}

	@Override
	public double getJobReward() {

		double totalReward = 0;

		for (int i = 0; i < numberItems(); i++) {
			int j = item.getIndex(items[i]);
			double utility = item.getReward(j) * quantity[i];
			totalReward += utility;

		}
		return totalReward;
	}

	@Override
	public double getUtility() {
		double utility = getJobReward() / numberItems();
		return utility;
	}
	
	public String toString(){
		String string = JobID + "";
		for (int i = 0; i < items.length; i++){
			string+= " " + items[i] + " " + quantity[i];
		}
		return string;
	}


}
