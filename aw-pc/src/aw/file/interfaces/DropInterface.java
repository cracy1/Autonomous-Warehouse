package aw.file.interfaces;

public interface DropInterface {
	public int getX(int index);
	public int getY(int index);
	public int numberDrops();
	public void sortClosestDropToRobot(int i, int j);
}
