package aw.file.interfaces;

import aw.file.ItemList;

public interface JobInterface {
	public int numberItems();
	public ItemList getNext();
	public double getUtility();
	public void sort();
}
