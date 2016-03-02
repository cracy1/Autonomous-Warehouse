package aw.file.interfaces;

import aw.file.ItemList;

public interface JobInterface {
	public int numberItems();
	public ItemList getNext();
	public double getJobReward();
	public void sort();
	public double getUtility();
	public double getItemReward(int i);
}
