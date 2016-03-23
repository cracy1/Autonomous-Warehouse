package aw.GUI;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aw.test.Node;

/**
 * Master component that will display the job information and the grid view.
 * @author jon woodburn
 *
 */

public class InformationComponent extends JPanel{
	private Grid grid;
	private InformationView view;
	private InformationModel model;
	
	public InformationComponent(InformationModel model, Grid grid) {
		super();
		this.grid = grid;
		grid.setOpaque(false);
		this.model = model;
		view = new InformationView(model);
		view.setOpaque(false);
//The grid and information panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 2));
		model.addObserver(view);
		infoPanel.add(grid);
		infoPanel.add(view);
		infoPanel.setOpaque(false);
		
		
//The title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		JLabel title = new JLabel("WAREHOUSE MANAGEMENT USER INTERFACE");
		title.setFont(new Font("Arial Black", Font.BOLD + Font.ITALIC, 24));
		
		titlePanel.add(title);
		
//The master panel		
		
		setLayout(new BorderLayout());
		add(infoPanel, BorderLayout.CENTER);
		add(titlePanel, BorderLayout.NORTH);
		
	}
}
