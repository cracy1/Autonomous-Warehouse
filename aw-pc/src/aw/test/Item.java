package aw.test;

public class Item {
	private String name;
	private double reward;
	private double weight;
	private int x, y;
	
	public Item(String name, double reward, double weight, int x, int y){
		this.name = name;
		this.reward = reward;
		this.weight = weight;
		this.x = x;
		this.y = y;
	}
	
	
	public String getName(int index) {
		return name;
	}

	public double getReward(int index) {
		return reward;
	}

	public double getWeight(int index) {
		return weight;
	}

	public int getX() {
		return x;
	}

	public int getY(int index) {
		return y;
	}
}
