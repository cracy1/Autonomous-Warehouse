package aw.GUI;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;

public class Grid extends JPanel{
	private ArrayList<Line2D.Double> xLines;
	private ArrayList<Line2D.Double> yLines; 
	
	int recX = 50;
	int recY = 500;
	int recWidth = 30;
	int recHeight = 30;
	int recCenterX;
	int recCenterY;
	
	//private Ellipse2D.Double robot;
	
	public Grid() {
		super();
		
		recCenterX = (recX + (recWidth/2));
		recCenterY = (recY + (recHeight/2));
		ActionListener movingRec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recY == 150 && recX >= 50 && recX < 600) {
					recX++;
					repaint();
				}
				else if (recX == 600 && recY >= 150 && recY < 500) {
					recY++;
					repaint();
				}
				else if(recY == 500 && recX <= 600 && recX > 50) {
				recX--;
				repaint();
				}
				else if(recX == 50 && recY<= 500 && recY > 150){
					recY--;
					repaint();
				}
			}
		};
		
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
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		draw(g2);
		g2.setColor(Color.RED);
		g2.fillRect(recX, recY, recWidth, recHeight);
	}
}
	
