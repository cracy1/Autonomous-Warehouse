package aw.GUI;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class GridComponent extends JComponent{
	private Grid grid;
	
	public GridComponent() {
		super();
		grid = new Grid();
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		grid.draw(g2);
	}
}
