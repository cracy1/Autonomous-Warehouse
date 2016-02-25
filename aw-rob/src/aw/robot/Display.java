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
	private Graphics g = new Graphics();
	
	public Display(){
		screenHeight = LCD.SCREEN_HEIGHT;
		screenWidth = LCD.SCREEN_WIDTH;
		
	}
	
	public int requestItem(String item,int amount){
		this.item = item;
		this.amount = amount;
		
		for(int i = 0; i < amount; i++){
			g.setFont(Font.getDefaultFont());
			g.drawString("I need ", screenWidth/2, 0, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(Integer.toString(amount - i), screenWidth/2, screenHeight/8, Graphics.HCENTER, true);
			
			g.setFont(Font.getDefaultFont());
			g.drawString("more " + item, screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
			
			g.setFont(Font.getSmallFont());
			g.drawString("If", screenWidth/2, screenHeight*7/10, Graphics.HCENTER);
			g.drawString("I am in the wrong place", screenWidth/2, screenHeight*8/10, Graphics.HCENTER);
			g.drawString("press the escape button", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
			
			int buttonPress = Button.waitForAnyPress();
			if(buttonPress == Button.ID_ESCAPE){
				return 0;
			}
			else{
				g.clear();
				continue;
			}
		}
		Sound.systemSound(false, 2);
		return 1;
	}
}
