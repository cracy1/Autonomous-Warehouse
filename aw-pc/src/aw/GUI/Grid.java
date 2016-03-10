package aw.GUI;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import aw.routePlanning.AStar;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

public class Grid extends JPanel{
	private ArrayList<Line2D.Double> xLines;
	private ArrayList<Line2D.Double> yLines; 
	

	private int recWidth = 30;
	private int recHeight = 30;
	private int recCenterX = 50;
	private int recCenterY = 100;
	private int height,width, xSize, ySize, recX, recY, xConvert, yConvert;
	private GridMap grid;
	
	private ArrayList<Point> route1;
	private ArrayList<Point> route2;
	private ArrayList<Point> route3;
	
	public Grid() {
		super();
		this.grid = MapUtils.createRealWarehouse(); 
		this.xSize = grid.getXSize();
		this.ySize = grid.getYSize();
		this.height = 800;
		this.width = 800;
		route1 = new ArrayList<Point>();
		
		
//Square just goes around the perimeter of the grid for the moment
//Just tester code for the moment.
		recX = recCenterX - (recWidth /2);
		recY = recCenterY - (recHeight/2);
		ActionListener movingRec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(recCenterY == 100 && recCenterX >= 50 && recCenterX < 50 * xSize) {
//					recCenterX++;
//					recX = recCenterX - (recWidth /2);
//					recY = recCenterY - (recHeight/2);
//					
//					repaint();
//				}
//				else if (recCenterX == 600 && recCenterY >= 100 && recCenterY < 450) {
//					recCenterY++;
//					recX = recCenterX - (recWidth /2);
//					recY = recCenterY - (recHeight/2);
//					repaint();
//				}
//				else if(recCenterY == 450 && recCenterX <= 600 && recCenterX > 50) {
//					recCenterX--;
//					recX = recCenterX - (recWidth /2);
//					recY = recCenterY - (recHeight/2);
//					repaint();
//				}
//				else if(recCenterX == 50 && recCenterY<= 450 && recCenterY > 100){
//					recCenterY--;
//					recX = recCenterX - (recWidth /2);
//					recY = recCenterY - (recHeight/2);
//					repaint();
//				}
				if(!route1.isEmpty()){
					
					Point nextCoord = route1.get(0);
					if(recCenterX < nextCoord.getX()*50 + 50){
						recCenterX++;
						recX = recCenterX - (recWidth /2);
						recY = recCenterY - (recHeight/2);
						
						repaint();
					}
					else if(recCenterX > nextCoord.getX()*50 + 50){
						recCenterX--;
						recX = recCenterX - (recWidth /2);
						recY = recCenterY - (recHeight/2);
						
						repaint();
					}
					else if(recCenterY < nextCoord.getY()*50 + 100){
						recCenterY++;
						recX = recCenterX - (recWidth /2);
						recY = recCenterY - (recHeight/2);
						
						repaint();
					}
					else if(recCenterY > nextCoord.getY()*50 + 100){
						recCenterY--;
						recX = recCenterX - (recWidth /2);
						recY = recCenterY - (recHeight/2);
						
						repaint();
					}
					//Removes nodes of the route that the robot has passed
					if((int)route1.get(0).getX()*50 + 50 == recCenterX && (int)route1.get(0).getY()*50 + 100 == recCenterY){
						route1.remove(0);
					}
				}
			}
		};
//Updates the position of the red square every 20 milliseconds.		
		Timer timer = new Timer(20, movingRec);
		
		timer.start();
		
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		for(int i = 0; i < ySize; i++) {
			g2.drawLine(50, (i *50) + 100,  50 * xSize, (i*50) + 100);
		}
		for(int i = 0; i < xSize; i++) {
			g2.drawLine(50 + (i*50), 100, 50 + (i*50), 50* ySize + 50);
		}
		
		for(int i = 0; i < xSize; i++){
			for(int j = 0; j < ySize; j++){
				if(grid.isObstructed(i, j)){
					g2.setColor(Color.BLACK);
					g2.fillRect(25 + i * 50, 125 + j * 50, 50, 50);
				}
			}
		}
		
		//Drawing route of the robot
		if(!route1.isEmpty()){
			g2.setStroke(new BasicStroke(8));
			g2.setColor(Color.RED);
			g2.drawLine(recCenterX, recCenterY, (int)(50 + route1.get(0).getX()*50), (int)(100 + route1.get(0).getY()*50));
			for(int i = 0; i < route1.size() - 1; i++){
				g2.drawLine((int)(50 + route1.get(i).getX()*50), (int)(100 + route1.get(i).getY()*50), (int)(50 + route1.get(i+1).getX()*50), (int)(100 + route1.get(i+1).getY()*50));
			}
			g2.drawOval((int)(50 + route1.get(route1.size() - 1).getX()*50) - 5, (int)(95 + route1.get(route1.size() - 1).getY()*50), 10, 10);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
//makes the square moving a lot smoother.
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		draw(g2);
		float thickness = 5;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(thickness));
		
		
		g2.setColor(Color.RED);
		g2.drawRect(recX, recY, recWidth, recHeight);
	}
	
	//Sets the routes of the different robots to a locally stored variable
	public void setRoute(ArrayList<Point> route, String robot){
		if(robot.equals("Ricardo")){
			route1 = route;
			recCenterX = (int)route1.get(0).getX()*50 + 50;
			recCenterY = (int)route1.get(0).getY()*50 + 100;
			route1.remove(0);
			
		}
		else if(robot.equals("NXT")){
			route2 = route;		
		}
		else{
			route3 = route;
		}
	}
}
	
