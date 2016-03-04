package aw.motion;

public class ItemRequest {
	private String name;
	private int amount;
	
	public ItemRequest(String name, int amount){
		this.name = name;
		this.amount = amount;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAmount(){
		return amount;
	}
}
