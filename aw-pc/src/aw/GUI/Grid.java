package aw.GUI;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

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
	
	private int recX;
	private int recY ;
	private int recWidth = 30;
	private int recHeight = 30;
	private int recCenterX = 50;
	private int recCenterY = 500;
	
	
	
	public Grid() {
		super();
//Square just goes around the perimeter of the grid for the moment
//Just tester code for the moment.
		recX = recCenterX - (recWidth /2);
		recY = recCenterY - (recHeight/2);
		ActionListener movingRec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recCenterY == 150 && recCenterX >= 50 && recCenterX < 600) {
					recCenterX++;
					recX = recCenterX - (recWidth /2);
					recY = recCenterY - (recHeight/2);
					
					repaint();
				}
				else if (recCenterX == 600 && recCenterY >= 150 && recCenterY < 500) {
					recCenterY++;
					recX = recCenterX - (recWidth /2);
					recY = recCenterY - (recHeight/2);
					repaint();
				}
				else if(recCenterY == 500 && recCenterX <= 600 && recCenterX > 50) {
				recCenterX--;
				recX = recCenterX - (recWidth /2);
				recY = recCenterY - (recHeight/2);
				repaint();
				}
				else if(recCenterX == 50 && recCenterY<= 500 && recCenterY > 150){
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
		
		xLines = new ArrayList<Line2D.Double>();
		yLines = new ArrayList<Line2D.Double>();

		for(int i = 1; i <= 8; i++){
			xLines.add(new Line2D.Double(50, (i*50) + 100, 600, (i*50) + 100));
		}
		for(int j = 1; j <=12; j++){
			yLines.add(new Line2D.Double(j*50, 150, j*50, 500));
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		for(int i = 0; i < 8; i++){
			g2.fill(xLines.get(i));
			g2.draw(xLines.get(i));
		}
		for(int j = 0; j < 12; j++) {
			g2.fill(yLines.get(j));
			g2.draw(yLines.get(j));
		}
		
		
		for(int i=0; i < 4; i++) {
			g2.fillRect(75 + i* 150, 225, 50, 250);
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
	
