package aw.test;

import java.util.LinkedList;

import aw.file.ItemList;
import aw.file.interfaces.JobInterface;

public class Job implements JobInterface {
	private LinkedList<ItemList> items;
	private int id;
	
	public Job(int id){
		items = new LinkedList<>();
		this.id = id;
	}
	
	public void addItem(ItemList item){
		items.add(item);
	}
	
	@Override
	public int numberItems() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemList getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getUtility() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

}
