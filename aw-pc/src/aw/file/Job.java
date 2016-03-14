package aw.file;

import java.util.ArrayList;

import aw.file.interfaces.JobInterface;

// try to extend Comparable

public class Job implements JobInterface {

	private String[] items;
	private int[] quantity;
	private int JobID;
	private int counter;
	private ItemList item = new ItemList();
	private int index;

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

		// sort();

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

	// to be modified

	@Override
	public void sort() {
		for (int i = 0; i < items.length - 1; i++) {
			if (getItemReward(i) < getItemReward(i + 1)) {
				String temp = items[i];
				items[i] = items[i + 1];
				items[i + 1] = temp;

				int y = quantity[i];
				quantity[i] = quantity[i + 1];
				quantity[i + 1] = y;
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

	public String toString() {
		String string = JobID + "";
		for (int i = 0; i < items.length; i++) {
			string += " " + items[i] + " " + quantity[i];
		}
		return string;
	}

	// using Manhattan distance

	public int getDistance(int x1, int y1, int x2, int y2) {

		return Math.abs(x2 - x1) + Math.abs(y2 - y1);

	}

	public void sortItems(int xCoord, int yCoord) {
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<String> P = new ArrayList<String>();
		for (int i = 0; i < numberItems(); i++) {
			P.add(items[i]);
		}
		System.out.println("Initial P = " + P);
		//int index;
		int x = xCoord; int y = yCoord;
//		int xTemp = -1, yTemp = -1;
		int sizeOfP = P.size();
		System.out.println("Initial size of P = " + sizeOfP);
		while (sizeOfP != 0) {
			int i = 0;
			int minDist = Integer.MAX_VALUE;
			if(!res.isEmpty()){
				while(i < sizeOfP) {
					minDist = Integer.MAX_VALUE;
					ItemList item = new ItemList();
					String[] temp = new String[res.size()];
					for(int j = 0; j < res.size(); j++ ){
						res.toArray(temp);
						if (minDist > getDistance(item.getX(item.getIndex(temp[j])), item.getY(item.getIndex(temp[j])), item.getX(item.getIndex(items[i])),item.getY(item.getIndex(items[i])))) {
							minDist = getDistance(item.getX(item.getIndex(temp[j])), item.getY(item.getIndex(temp[j])), item.getX(item.getIndex(items[i])), item.getY(item.getIndex(items[i])));
							index = i;
						}
						System.out.println("items[j]:" + items[j]);
					}
					res.add(item.getName(item.getIndex(items[index])));
					P.remove(index); 
					P.trimToSize();
					sizeOfP--;
					i++;
					System.out.println(P.size());
					System.out.println("P: " + P);
					System.out.println(res.size());
					System.out.println("res: " + res);
					System.out.println("item removed from P and added to res: " + items[index]);
					System.out.println();
					
				}
			}
			else{
				while(i < sizeOfP){
					ItemList item = new ItemList();
					if (minDist > getDistance(x, y, item.getX(item.getIndex(items[i])),item.getY(item.getIndex(items[i])))) {
						minDist = getDistance(x, y, item.getX(item.getIndex(items[i])), item.getY(item.getIndex(items[i])));
						index = i;
						}
					i++;
				}
				res.add(item.getName(item.getIndex(items[index])));
				P.remove(index); 
				P.trimToSize();
				sizeOfP--;
				System.out.println("res: " + res);
			}
				


			}

//			System.out.println(res.size());
		}

		// for (int i = 0; i < P.size(); i++) {
		// ItemList item = new ItemList();
		//
		// }
//	
//	private String closest(String[] a){
//		for(int i = 0; i < a.length; i ++){
//		return null;
//		}
	
	}


	// public void tsp(int[] arr ){
	// int i = 0;
	//
	//
	//
	// for(i = 0; i < arr.length; i++){
	//
	//
	// }
	// }


