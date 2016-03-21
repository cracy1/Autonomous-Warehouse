package aw.file;

import java.util.ArrayList;

import aw.file.interfaces.JobInterface;

public class Job implements JobInterface {

	private String[] items;
	private int[] quantity;
	private int JobID;
	private int counter;
	private ItemList item = new ItemList();

	/*
	 * The constructor initialises and builds array where information about the job is stored. Each index corresponds to one unique 
	 * item in the job, and this index is common through both arrays.
	 * @param row the string that represents the job in its raw state
	 */
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
	
	/*
	 * Gets the particular item at the given position in the array.
	 * @param index the position of the item in the array
	 * @return the item at the given index
	 */
	@Override
	public String getItem(int index) {
		return items[index];
	}

	/*
	 * Gets the quantity of the particular item at the given position in the array.
	 * @param index the position of the item that has its quantity on the same position in a different array.
	 * @return the quantity of the item at the given index
	 */
	@Override
	public int getQuantity(int index) {
		return quantity[index];
	}
	
	/*
	 * Gets the number of items in a job.
	 * @return the number of items in the job
	 */
	@Override
	public int numberItems() {
		return counter / 2;
	}

	/*
	 * Gets the id of the job.
	 * @return the id of the job
	 */
	@Override
	public int getID() {
		return JobID;
	}
	
	/*
	 * Calculates the utility of the job, represented by the job reward divided by the number of items in the job.
	 * @return the utility of the job
	 */
	@Override
	public double getUtility() {
		double utility = getJobReward() / numberItems();
		return utility;
	}
	
	/*
	 * Gets the index of a particular item in the job.
	 * @param item the item whose index should be found
	 * @return the index of the item 
	 */
	private int getItemIndexInJob(String item) {
		int i = 0;
		while (i < items.length) {
			if (items[i].equals(item))
				break;
			i++;
		}
		return i;
	}

	/*
	 * Gets the reward of the item in a job considering the fact that a job can contain an item more than once.
	 * @param index the index of the item whose reward should be found
	 * @return the reward of the item 
	 */
	@Override
	public double getItemReward(int index) {
		int j = item.getIndex(items[index]);
		double reward = item.getReward(j) * quantity[index];
		return reward;
	}
	
	/*
	 * Gets the reward of the job by summing up the rewards of all the items in the job.
	 * @return the reward of the job
	 */
	@Override
	public double getJobReward() {

		double totalReward = 0;

		for (int i = 0; i < numberItems(); i++) {
			double reward = getItemReward(i);
			totalReward += reward;

		}
		return totalReward;
	}

	/*
	 * Gets the distance between 2 points using the Manhattan distance.
	 * @return the Manhattan distance between the 2 points
	 */
	private int getDistance(int x1, int y1, int x2, int y2) {

		return Math.abs(x2 - x1) + Math.abs(y2 - y1);

	}
	/*
	 * Sorts the items in the job using a sub-optimal TSP algorithm by taking the robot's position into account.
	 * @param xCoord the x coordinate of the position of the robot
	 * @param yCoord the y coordinate of the position of the robot
	 */
	@Override
	public void sort(int xCoord, int yCoord) {
		ArrayList<String> res = new ArrayList<String>();			// the array list that will contain the items in the right order
		ArrayList<String> P = new ArrayList<String>();				// the array list that contains the items in the current order
		ArrayList<Integer> quantityList = new ArrayList<Integer>();	// the array list that will contain the quantity of items in the right order
		for (int i = 0; i < numberItems(); i++) {
			P.add(items[i]);
		}
		String closestItem = "";
		int index = 0;
		while (!P.isEmpty()) {
			int minDist = Integer.MAX_VALUE;
			if(!res.isEmpty()){										// if the array list is empty then it adds the closest item to the robot's current position
				String[] Ptemp = new String[P.size()];
				P.toArray(Ptemp);									// converting the array list into an array for easier access to elements
				for(int i = 0; i < P.size(); i++) {
					ItemList item = new ItemList();
					String[] temp = new String[res.size()];
					for(int j = 0; j < res.size(); j++){
						res.toArray(temp);							// converting the array list into an array for easier access to elements
						int distance = getDistance(item.getX(item.getIndex(temp[j])), item.getY(item.getIndex(temp[j])), item.getX(item.getIndex(Ptemp[i])),item.getY(item.getIndex(Ptemp[i])));
						if (minDist > distance) {
							minDist = distance;
							closestItem = Ptemp[i];					// keeps track of the item in P closest to any items in res
							index = i;								// keeps track of the index of the item
						}
					}
				}
				res.add(item.getName(item.getIndex(closestItem)));
				quantityList.add(quantity[getItemIndexInJob(closestItem)]);
				P.remove(index); 
				P.trimToSize();
			}
			
			else{
				String[] Ptemp = new String[P.size()];
				P.toArray(Ptemp);									// converting the array list into an array for easier access to elements
				for(int i = 0; i < P.size(); i++){
					ItemList item = new ItemList();
					int distance = getDistance(xCoord, yCoord, item.getX(item.getIndex(Ptemp[i])),item.getY(item.getIndex(Ptemp[i])));
					if (minDist > distance) {
						minDist = distance;
						closestItem = Ptemp[i];						// keeps track of the item in P closest to the robot's current position
						index = i;									// keeps track of the index of the item
						}
				}
				res.add(item.getName(item.getIndex(items[index])));
				quantityList.add(quantity[getItemIndexInJob(closestItem)]);
				P.remove(index); 
				P.trimToSize();
			}
		}
		res.toArray(items);											// replaces our initial unsorted array of items with the sorted one
		for(int i = 0; i < quantityList.size(); i++)
			quantity[i] = quantityList.get(i);						// replaces our initial unsorted array of quantities with the sorted one
	}
}
