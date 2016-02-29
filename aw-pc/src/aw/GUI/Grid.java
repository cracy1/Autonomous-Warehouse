package aw.GUI;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;

public class Grid extends JPanel{
	private ArrayList<Line2D.Double> xLines;
	private ArrayList<Line2D.Double> yLines; 
	//private Ellipse2D.Double robot;
	
	public Grid() {
		xLines = new ArrayList<Line2D.Double>();
		yLines = new ArrayList<Line2D.Double>();

		for(int i = 1; i <= 8; i++){
			xLines.add(new Line2D.Double(50, (i*50) + 100, 600, (i*50) + 100));
		}
		for(int j = 1; j <=12; j++){
			yLines.add(new Line2D.Double(j*50, 150, j*50, 500));
		}
	}
	
	public void paintComponent(Graphics g) {
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
		
	}
}
	
