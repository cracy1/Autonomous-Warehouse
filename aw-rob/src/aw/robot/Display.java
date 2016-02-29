package aw.robot;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class Display {
	
	private int screenHeight;
	private int screenWidth;
	private Graphics g = new Graphics();
	
	public Display(){
		screenHeight = LCD.SCREEN_HEIGHT;
		screenWidth = LCD.SCREEN_WIDTH;
		
	}
	
	private void drawArrows(){
		g.drawLine(screenWidth/4, screenHeight/2, screenWidth/8, screenHeight/2); //Left arrow body
		g.drawLine(screenWidth/8, screenHeight/2, screenWidth*3/16, screenHeight*7/16); //Left arrow up
		g.drawLine(screenWidth/8, screenHeight/2, screenWidth*3/16, screenHeight*9/16); //Left arrow down
		
		g.drawLine(screenWidth*3/4, screenHeight/2, screenWidth*7/8, screenHeight/2); //Right arrow body
		g.drawLine(screenWidth*7/8, screenHeight/2, screenWidth*13/16, screenHeight*7/16); //Right arrow up
		g.drawLine(screenWidth*7/8, screenHeight/2, screenWidth*13/16, screenHeight*9/16); //Right arrow down
	}
	
	//Outputs:
	// 1 if successful
	// 0 if failed
	public int requestItem(String item,int amount){
		g.clear();
		
		for(int i = 0; i < amount; i++){
			g.setFont(Font.getDefaultFont());
			g.drawString("I need ", screenWidth/2, 0, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(Integer.toString(amount - i), screenWidth/2, screenHeight/8, Graphics.HCENTER);
			
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
	
	public String setLocation(){
		boolean xInput = true;
		boolean yInput = false;
		boolean dInput = false;
		Integer xCoord = 0;
		Integer yCoord = 0;
		Integer direction = 0;
		while(xInput){
			g.setFont(Font.getDefaultFont());
			g.drawString("X Coordinate:", screenWidth/2, screenHeight/4, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(xCoord.toString(), screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
			
			g.setFont(Font.getSmallFont());
			g.drawString("To confirm, press Enter", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
			
			drawArrows();
			
			int buttonPress = Button.waitForAnyPress();
			if(buttonPress == Button.ID_RIGHT){
				if(xCoord < 0){
					xCoord = 0;
				}
				else{
					xCoord++;
				}
			}
			else if(buttonPress == Button.ID_LEFT){
				if(xCoord <= 0){
					xCoord = 0;
				}
				else{
					xCoord--;
				}
			}
			else if(buttonPress == Button.ID_ENTER){
				xInput = false;
				yInput = true;
			}
			g.clear();
		}
		
		while(yInput){
			g.setFont(Font.getDefaultFont());
			g.drawString("Y Coordinate:", screenWidth/2, screenHeight/4, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(yCoord.toString(), screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
			
			g.setFont(Font.getSmallFont());
			g.drawString("To confirm, press Enter", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
			
			drawArrows();
			
			int buttonPress = Button.waitForAnyPress();
			if(buttonPress == Button.ID_RIGHT){
				if(yCoord < 0){
					yCoord = 0;
				}
				else{
					yCoord++;
				}
			}
			else if(buttonPress == Button.ID_LEFT){
				if(yCoord <= 0){
					yCoord = 0;
				}
				else{
					yCoord--;
				}
			}
			else if(buttonPress == Button.ID_ENTER){
				yInput = false;
				dInput = true;
			}
			g.clear();
		}
		
		while(dInput){
			g.setFont(Font.getDefaultFont());
			g.drawString("Direction:", screenWidth/2, screenHeight/4, Graphics.HCENTER);
			
			g.setFont(Font.getLargeFont());
			g.drawString(direction.toString(), screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
			
			g.setFont(Font.getSmallFont());
			g.drawString("To confirm, press Enter", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
			
			drawArrows();
			
			int buttonPress = Button.waitForAnyPress();
			if(buttonPress == Button.ID_RIGHT){
				if(direction < 0){
					direction = 0;
				}
				else if(direction >= 270){
					direction = 270;
				}
				else{
					direction = direction + 90;
				}
			}
			else if(buttonPress == Button.ID_LEFT){
				if(direction <= 0){
					direction = 0;
				}
				else if(direction > 270){
					direction = 270;
				}
				else{
					direction =direction - 90;
				}
			}
			else if(buttonPress == Button.ID_ENTER){
				dInput = false;
			}
			g.clear();
		}
		
		return (xCoord.toString() + yCoord.toString() + direction.toString());
	}
	
	
}
