package aw.file.interfaces;

public interface JobInterface {
	public String getItem(int index);
	public int getQuantity(int index);
	public int numberItems();
	public int getID();
	public double getUtility();
	public double getJobReward();
	public double getItemReward(int index);
	public void sort(int i, int j);
}
