package aw.display;



import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import aw.test.Node;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

public class Display {
	
	private int screenHeight;
	private int screenWidth;
	private Graphics g = new Graphics();
	
	private int direction;
	private int x;
	private int y;
	
	private int goalX;
	private int goalY;
	private String name;
	private int amount;
	
	public Display(){
		screenHeight = LCD.SCREEN_HEIGHT;
		screenWidth = LCD.SCREEN_WIDTH;
		
	}
	
	public void draw(Node current, Node goal, String item, int amount){
		int currentX = current.x;
		int currentY = current.y;
		int goalX = goal.x;
		int goalY = goal.y;
		
		if(currentX == goalX && currentY == goalY){
			requestItem(item, amount);
		}
		else{
			showPosition(currentX, currentY, goalX, goalY);
		}
	}
	
	public void move(String c){
		if(c.equals("f")){
			if(direction == 0){
				x++;
			}
			else if(direction == 90){
				y--;
			}
			if(direction == 180){
				x--;
			}
			if(direction == 270){
				y++;
			}
			showPosition(x, y, goalX, goalY);
		}
		else if(c.equals("l")){
			direction = direction - 90;
			if(direction < 0){
				direction = direction + 360;
			}
		}
		else if(c.equals("r")){
			direction = direction + 90;
			if(direction > 270){
				direction = direction - 360;
			}
		}
		else if(c.equals("t")){
			direction = direction + 180;
			if(direction > 270){
				direction = direction - 360;
			}
		}
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
	
	public int dropOffItem(String item,int amount, int delay){
		g.clear();
		
		g.setFont(Font.getDefaultFont());
		g.drawString("I am dropping off ", screenWidth/2, 0, Graphics.HCENTER);
		
		g.setFont(Font.getLargeFont());
		g.drawString(Integer.toString(amount), screenWidth/2, screenHeight/8, Graphics.HCENTER);
		
		g.setFont(Font.getDefaultFont());
		g.drawString(item, screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
	
		g.setFont(Font.getSmallFont());
		g.drawString("If", screenWidth/2, screenHeight*7/10, Graphics.HCENTER);
		g.drawString("I am in the wrong place", screenWidth/2, screenHeight*8/10, Graphics.HCENTER);
		g.drawString("press the escape button", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
			
		Delay.msDelay(delay);

		Sound.systemSound(false, 2);
		return 1;
	}
	
	private void drawArrows(){
		g.drawLine(screenWidth/4, screenHeight/2, screenWidth/8, screenHeight/2); //Left arrow body
		g.drawLine(screenWidth/8, screenHeight/2, screenWidth*3/16, screenHeight*7/16); //Left arrow up
		g.drawLine(screenWidth/8, screenHeight/2, screenWidth*3/16, screenHeight*9/16); //Left arrow down
		
		g.drawLine(screenWidth*3/4, screenHeight/2, screenWidth*7/8, screenHeight/2); //Right arrow body
		g.drawLine(screenWidth*7/8, screenHeight/2, screenWidth*13/16, screenHeight*7/16); //Right arrow up
		g.drawLine(screenWidth*7/8, screenHeight/2, screenWidth*13/16, screenHeight*9/16); //Right arrow down
	}
	
	private void locationSetText(String name, Integer value){
		g.setFont(Font.getDefaultFont());
		g.drawString(name + ":", screenWidth/2, screenHeight/4, Graphics.HCENTER);
		
		g.setFont(Font.getLargeFont());
		g.drawString(value.toString(), screenWidth/2, screenHeight*3/8, Graphics.HCENTER);
		
		g.setFont(Font.getSmallFont());
		g.drawString("To confirm, press Enter", screenWidth/2, screenHeight*9/10, Graphics.HCENTER);
	}
	
	public String setLocation(){
		boolean xInput = true;
		boolean yInput = false;
		boolean dInput = false;
		Integer xCoord = 0;
		Integer yCoord = 0;
		Integer direction = 0;
		
		g.clear();
		
		while(xInput){
			locationSetText("X Coordinate", xCoord);
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
			locationSetText("Y Coordinate", yCoord);
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
			locationSetText("Direction", direction);
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
		
		return (xCoord.toString() + " " + yCoord.toString() + " " + direction.toString());
	}
	
	private String writeCoord(Integer x, Integer y){
		return ("(" + x + "," + y + ")");
	}
	
	public void showPosition(Integer currentCoordX, Integer currentCoordY, Integer goalCoordX, Integer goalCoordY){
		g.clear();
		
		g.setFont(Font.getDefaultFont());
		g.drawString("At", 2, 0, Graphics.LEFT);
		g.drawString("To", screenWidth/2 + 2, 0, Graphics.LEFT);
		g.drawString(writeCoord(currentCoordX, currentCoordY) , screenWidth/2 - 2, 0, Graphics.RIGHT);
		g.drawString(writeCoord(goalCoordX, goalCoordY), screenWidth - 2, 0, Graphics.RIGHT);
		
		g.drawLine(screenWidth/2, 0, screenWidth/2, screenHeight/8);
		
		GridMap map = MapUtils.createRealWarehouse();
		int xSize = map.getXSize();
		int ySize = map.getYSize();
		int xConvert = screenWidth/xSize;
		int yConvert = (screenHeight*7/8)/ySize;
		
		g.drawLine(0, screenHeight/8, screenWidth - 1, screenHeight/8); //Top horizontal line
		g.drawLine(0, screenHeight - 1, screenWidth - 1, screenHeight - 1); //Bottom hoizontal line
		g.drawLine(0, 0, 0, screenHeight - 1); //Left vertical line
		g.drawLine(screenWidth - 1, 0, screenWidth - 1, screenHeight - 1); //Right vertical line
		
		for(int i = 0; i < xSize; i++){
			for(int j = 0; j < ySize; j++){
				if(map.isObstructed(i, j)){
					g.setColor(Graphics.BLACK);
					g.fillRect(i * xConvert, ((ySize - j) * yConvert), xConvert, yConvert);
				}
			}
		}
		
		g.drawRect(currentCoordX * xConvert, ((ySize - currentCoordY - 1) * yConvert) + screenHeight/8 - 1, xConvert, yConvert);
	}
}
























