package aw.GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JPanel;


public class InformationComponent extends JPanel{
	private Grid grid;
	private InformationView view;
	private InformationModel model;
	
	public InformationComponent(InformationModel model) {
		super();
		grid = new Grid();
		this.model = model;
		view = new InformationView(model);
		
		setLayout(new GridLayout(0, 2));
		model.addObserver(view);
		add(grid);
		add(view);
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		//grid.draw(g2);
		//view.draw(g2);
	}

}
