package aw.GUI;

import java.util.LinkedList;

import javax.swing.JPanel;

import aw.comms.BluetoothCommandListener;
import aw.comms.Communication;
import aw.file.Drop;
import aw.test.Node;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that displays the grid and the robots
 * @author jon woodburn and dominic trott
 *
 */
public class Grid extends JPanel implements BluetoothCommandListener{
	private int robotWidth = 30;
	private int robotHeight = 30;
	private int robot1CenterX = 50;
	private int robot1CenterY = 450;
	private int robot2CenterX = 50;
	private int robot2CenterY = 100;
	private int robot3CenterX = 600;
	private int robot3CenterY = 450;
	private int height,width, xSize, ySize, robot1X, robot1Y, robot2X, robot2Y, robot3X, robot3Y, xConvert, yConvert;
	private GridMap grid;
	private Drop drop;
	
	private LinkedList<Node> route1;
	private LinkedList<LinkedList<Node>> route1ss;
	private LinkedList<Node> route2;
	private LinkedList<LinkedList<Node>> route2ss;
	private LinkedList<Node> route3;
	private LinkedList<LinkedList<Node>> route3ss;
	
	private Color green = new Color(34, 139, 34);
	private Color red = new Color(205, 0, 0);
	private Color blue = new Color(0, 154, 205);
	
	
	public Grid() {
		super();
		this.grid = MapUtils.createMarkingWarehouseMap(); 
		this.xSize = grid.getXSize();
		this.ySize = grid.getYSize();
		this.height = 800;
		this.width = 800;
		route1 = new LinkedList<Node>();
		route1ss = new LinkedList<LinkedList<Node>>();
		route2 = new LinkedList<Node>();
		route2ss = new LinkedList<LinkedList<Node>>();
		route3 = new LinkedList<Node>();
		route3ss = new LinkedList<LinkedList<Node>>();
		drop = new Drop();
		
		
//Sets the robot positions.
		robot1X = robot1CenterX - (robotWidth /2);
		robot1Y = robot1CenterY - (robotHeight/2);
		
		robot2X = robot2CenterX - (robotWidth /2);
		robot2Y = robot2CenterY - (robotHeight/2);
		
		robot3X = robot3CenterX - (robotWidth /2);
		robot3Y = robot3CenterY - (robotHeight/2);
		
//Makes the class listen into commands sent from the robots to the PC.
		
		//Communication.getRobotConnection("Ricardo").getCommandReceiver().addBluetoothCommandListener(this);
		Communication.getRobotConnection("NXT").getCommandReceiver().addBluetoothCommandListener(this);
	}
	
	/**
	 * Draws the route of the robot
	 * @param route the route to be drawn
	 * @param robotCenterX The x coord of the center of the square representing the robot
	 * @param robotCenterY The y coord of the center of the square representing the robot
	 * @param g2 Graphics
	 * @param color the colour 
	 */
	
	private void drawRoute(LinkedList<LinkedList<Node>> routess, int robotCenterX, int robotCenterY, Graphics2D g2, Color color){
		g2.setStroke(new BasicStroke(8));
		g2.setColor(color);
		if(routess.get(0).isEmpty()){
			routess.remove(0);
		}
		
		g2.drawLine(robotCenterX, robotCenterY, (int)(50 + routess.get(0).get(0).x*50), (int)(450 - routess.get(0).get(0).y*50));
		LinkedList<Node> route = routess.getFirst();
		for(int i = 0; i < route.size() - 1; i++){
			g2.drawLine((int)(50 + route.get(i).x*50), (int)(450 - route.get(i).y*50), (int)(50 + route.get(i+1).x*50), (int)(450 - route.get(i+1).y*50));
		}
		g2.drawOval((int)(50 + route.get(route.size() - 1).x*50) - 5, (int)(445 - route.get(route.size() - 1).y*50), 10, 10);
	}
	
	/**
	 * Draws the grid, the obstacles (base on GridMap, and draws the route of the robots.
	 * @param g the graphics component
	 */
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		for(int i = 0; i < ySize; i++) {
			g2.drawLine(50, 450 - (i *50),  50 * xSize, 450 - (i*50) );
		}
		for(int i = 0; i < xSize; i++) {
			g2.drawLine(50 + (i*50), 100, 50 + (i*50), 50* ySize + 50);
		}
		
		for(int i = 0; i < xSize; i++){
			for(int j = 0; j < ySize; j++){
				if(grid.isObstructed(i, j)){
					g2.setColor(Color.BLACK);
					g2.fillRect(25 + i * 50, 425 - j * 50, 50, 50);
				}
			}
		}
		
		//Drawing route of the robot
		if(!route1.isEmpty()){
			drawRoute(route1ss, robot1CenterX, robot1CenterY, g2, red);
		}
		
		if(!route2ss.isEmpty()){
			drawRoute(route2ss, robot2CenterX, robot2CenterY, g2, blue);
		}
		if(!route3.isEmpty()){
			drawRoute(route3ss, robot3CenterX, robot3CenterY, g2, green);
		}
	}
	
	/**
	 * Draws the drop points and the rectangles which represent the robot.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
//Drop locations		
		int xDrop1 = drop.getX(0) * 50 +50;
		int yDrop1 = 450 - drop.getY(0) *50;
		int xDrop2 = drop.getX(1) *50 +50;
		int yDrop2 = 450 - drop.getY(1) * 50;
		//makes the square moving a lot smoother.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		draw(g2);
		float thickness = 5;
		g2.setStroke(new BasicStroke(thickness));
		g2.setColor(Color.BLACK);
//Just a line to divide the frame into two
		g2.drawLine(630, 0, 630, 800);
//Draw the drops		
		g2.setColor(Color.DARK_GRAY);
		g2.drawLine(xDrop1, yDrop1 -20, xDrop1, yDrop1 +20);
		g2.drawLine(xDrop1 -20, yDrop1, xDrop1 +20, yDrop1);
		g2.drawLine(xDrop2, yDrop2 -20, xDrop2, yDrop2 +20);
		g2.drawLine(xDrop2 -20, yDrop2, xDrop2 +20, yDrop2);
		
		g2.setColor(red);
		g2.drawRect(robot1X, robot1Y, robotWidth, robotHeight);
		
		g2.setColor(blue);
		g2.drawRect(robot2X, robot2Y, robotWidth, robotHeight);
		
		g2.setColor(green);
		g2.drawRect(robot3X, robot3Y, robotWidth, robotHeight);
	}
	
	/**
	 * Sets the route for one of the robots
	 * @param route the route to be taken
	 * @param robot the name of the robot
	 */
	public void setRoute(LinkedList<Node> route, String robot){
		if(robot.equals("Ricardo")){
			if(route1.isEmpty()){
				route1 = route;
				robot1CenterX = (int)route1.get(0).x*50 + 50;
				robot1CenterY = (int)450 - route1.get(0).y*50;
				route1.remove(0);
			}
			else{
				route1.addAll(route);
			}
		}
		else if(robot.equals("NXT")){
			route2ss.add(route);
			if(route2ss.size() == 1){
				robot2CenterX = (int)route2ss.get(0).get(0).x*50 + 50;
				robot2CenterY = (int)450 - route2ss.get(0).get(0).y*50 ;
				route2ss.get(0).remove(0);
			}
			else{
				route2ss.getLast().remove(0);
			}
		}
		else if(robot.equals("Dave")){
			route3 = route;
			robot3CenterX = (int)route3.get(0).x*50 + 50;
			robot3CenterY = (int)450 - route3.get(0).y*50 ;
			route3.remove(0);
		}
		
		repaint();
	}
	
	/**
	 * Set the robot coordinates of a given robot
	 * @param name the name of the robot
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	
	public void setRobCoord(String name, int x, int y) {
		if(name.equals("Ricardo")) {
			this.robot1CenterX = x * 50 + 50;
			this.robot1CenterY = 450 - y *50;
		}
		else if(name.equals("NXT")) {
			this.robot2CenterX = x * 50 + 50;
			this.robot2CenterY = 450 - y *50 ;
		}
		else {
			this.robot3CenterX = x * 50 + 50;
			this.robot3CenterY = 450 - y *50 ;
		}
		
	}
	
	/**
	 * Listener method. Whenever a command is received, we check which robot sent it, and update its location on the map.
	 */

	@Override
	public void commandReceived(String name, String command) {
		if(command.equals("f")){
		if(name.equals("Ricardo")) {
//Draws the location of the first robot along its given route
			if(!route1.isEmpty()){
				Node nextCoord = route1.get(0);
				if(robot1CenterX < nextCoord.x*50 + 50){
					robot1CenterX = robot1CenterX + 50;
					robot1X = robot1CenterX - (robotWidth /2);
					robot1Y = robot1CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot1CenterX > nextCoord.x*50 + 50){
					robot1CenterX = robot1CenterX - 50;
					robot1X = robot1CenterX - (robotWidth /2);
					robot1Y = robot1CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot1CenterY > 450 - nextCoord.y*50 ){
					robot1CenterY = robot1CenterY - 50;
					robot1X = robot1CenterX - (robotWidth /2);
					robot1Y = robot1CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot1CenterY < 450 - nextCoord.y*50 ){
					robot1CenterY = robot1CenterY + 50;
					robot1X = robot1CenterX - (robotWidth /2);
					robot1Y = robot1CenterY - (robotHeight/2);
					
					repaint();
				}
				//Removes nodes of the route that the robot has passed
				if((int)route1.get(0).x*50 + 50 == robot1CenterX && (int) 450 - route1.get(0).y*50  == robot1CenterY){
					route1.remove(0);
				}
			}
		}
		else if (name.equals("NXT")) {
			//Draws the location of the second robot along its given route
			if(!route2ss.isEmpty()){
				Node nextCoord = route2ss.get(0).get(0);
				if(robot2CenterX < nextCoord.x*50 + 50){
					robot2CenterX += 50;
					robot2X = robot2CenterX - (robotWidth /2);
					robot2Y = robot2CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot2CenterX > nextCoord.x*50 + 50){
					robot2CenterX-= 50;
					robot2X = robot2CenterX - (robotWidth /2);
					robot2Y = robot2CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot2CenterY > 450 - nextCoord.y*50 ){
					robot2CenterY-= 50;
					robot2X = robot2CenterX - (robotWidth /2);
					robot2Y = robot2CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot2CenterY < 450 - nextCoord.y*50 ){
					robot2CenterY+= 50;
					robot2X = robot2CenterX - (robotWidth /2);
					robot2Y = robot2CenterY - (robotHeight/2);
					
					repaint();
				}
				//Removes nodes of the route that the robot has passed
				if((int)route2ss.get(0).get(0).x*50 + 50 == robot2CenterX && (int)450 - route2ss.get(0).get(0).y*50  == robot2CenterY){
					route2ss.get(0).remove(0);
				}
			}
			
		}
		else {
			//Draws the location of the third robot along its given route
			if(!route3.isEmpty()){
				Node nextCoord = route3.get(0);
				if(robot3CenterX < nextCoord.x*50 + 50){
					robot3CenterX+= 50;
					robot3X = robot3CenterX - (robotWidth /2);
					robot3Y = robot3CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot3CenterX > nextCoord.x*50 + 50){
					robot3CenterX-= 50;
					robot3X = robot3CenterX - (robotWidth /2);
					robot3Y = robot3CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot3CenterY > 450 - nextCoord.y*50){
					robot3CenterY-= 50;
					robot3X = robot3CenterX - (robotWidth /2);
					robot3Y = robot3CenterY - (robotHeight/2);
					
					repaint();
				}
				else if(robot3CenterY < 450 - nextCoord.y*50 ){
					robot3CenterY+=50;
					robot3X = robot3CenterX - (robotWidth /2);
					robot3Y = robot3CenterY - (robotHeight/2);
					
					repaint();
				}
				//Removes nodes of the route that the robot has passed
				if((int)route3.get(0).x*50 + 50 == robot3CenterX && (int)450 - route3.get(0).y*50 == robot3CenterY){
					route3.remove(0);
				}
			}
		}
		}
		
	}
}
	
