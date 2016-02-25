package aw.file.interfaces;

import aw.file.Item;

public interface JobInterface {
	public int numberItems();
	public Item getNext();
	public double getUtility();
	public void sort();
}
