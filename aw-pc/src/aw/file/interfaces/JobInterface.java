package aw.file.interfaces;

import aw.file.Item;

public interface JobInterface {
	public Item getNext();
	public double getUtility();
	public void sort();
}
