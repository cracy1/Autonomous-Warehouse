package aw.robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LCDOutputStream;
import lejos.nxt.Sound;

public class Display {
	
	private String item;
	private int amount;
	private int screenHeight;
	private int screenWidth;
	
	public Display(){
		screenHeight = LCD.CELL_HEIGHT;
		screenWidth = LCD.CELL_WIDTH;
		
	}
	
	public void requestItem(String item,int amount){
		this.item = item;
		this.amount = amount;
		
		for(int i = 0; i < amount; i++){
			LCD.drawString("I need ", screenWidth, (screenHeight/5)*2);
			LCD.drawString(Integer.toString(amount - i), 8, (screenHeight/5)*3, true);
			LCD.drawString("more " + item, screenWidth/2, (screenHeight/5)*4);
//			System.out.println("I need " + (amount - i) + " more " + item);
			Button.ENTER.waitForPress();
			LCD.clear();
		}
		Sound.systemSound(false, 2);
	}
}
