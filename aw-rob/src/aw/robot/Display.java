package aw.robot;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class Display {
	
	private String item;
	private int amount;
	private int screenHeight;
	private int screenWidth;
	private Graphics g;
	
	public Display(){
		screenHeight = LCD.SCREEN_HEIGHT;
		screenWidth = LCD.SCREEN_WIDTH;
		Graphics g = new Graphics();
		
	}
	
	public void requestItem(String item,int amount){
		this.item = item;
		this.amount = amount;
		
		for(int i = 0; i < amount; i++){
			g.drawString("I need ", screenWidth/2, 0, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(Integer.toString(amount - i), screenWidth/2, screenHeight/4, Graphics.HCENTER, true);
			
			g.setFont(Font.getDefaultFont());
			g.drawString("more " + item, screenWidth/2, screenHeight/2, Graphics.HCENTER);
			
			Button.ENTER.waitForPress();
			g.clear();
		}
		Sound.systemSound(false, 2);
	}
}
