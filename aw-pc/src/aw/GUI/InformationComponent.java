package aw.GUI;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class InformationComponent extends JPanel{
	private Grid grid;
	
	public InformationComponent(Information info) {
		super();
		grid = new Grid();
		InformationModel model = new InformationModel(info);
		InformationView view = new InformationView(model);
		
		model.addObserver(view);
		setLayout(new BorderLayout());
		add(grid, BorderLayout.CENTER);
		
		
	}
/*
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		grid.draw(g2);
	}
*/
}
