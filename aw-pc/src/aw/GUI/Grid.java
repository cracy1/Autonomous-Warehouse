package aw.GUI;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

import java.awt.Graphics2D;
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
	private int recCenterY = 450;
	private int height,width, xSize, ySize, recX, recY, xConvert, yConvert;
	private GridMap grid;
	
	
	
	public Grid() {
		super();
		this.grid = MapUtils.createRealWarehouse(); 
		this.xSize = grid.getXSize();
		this.ySize = grid.getYSize();
		this.height = 800;
		this.width = 800;
//Square just goes around the perimeter of the grid for the moment
//Just tester code for the moment.
		recX = recCenterX - (recWidth /2);
		recY = recCenterY - (recHeight/2);
		ActionListener movingRec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recCenterY == 100 && recCenterX >= 50 && recCenterX < 50 * xSize) {
					recCenterX++;
					recX = recCenterX - (recWidth /2);
					recY = recCenterY - (recHeight/2);
					
					repaint();
				}
				else if (recCenterX == 600 && recCenterY >= 100 && recCenterY < 450) {
					recCenterY++;
					recX = recCenterX - (recWidth /2);
					recY = recCenterY - (recHeight/2);
					repaint();
				}
				else if(recCenterY == 450 && recCenterX <= 600 && recCenterX > 50) {
				recCenterX--;
				recX = recCenterX - (recWidth /2);
				recY = recCenterY - (recHeight/2);
				repaint();
				}
				else if(recCenterX == 50 && recCenterY<= 450 && recCenterY > 100){
					recCenterY--;
					recX = recCenterX - (recWidth /2);
					recY = recCenterY - (recHeight/2);
					repaint();
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
}
	
