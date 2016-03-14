package aw.GUI;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aw.test.Node;


public class InformationComponent extends JPanel{
	private Grid grid;
	private InformationView view;
	private InformationModel model;
	
	public InformationComponent(InformationModel model) {
		super();
		grid = new Grid();
		this.model = model;
		view = new InformationView(model);
//The grid and information panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 2));
		model.addObserver(view);
		infoPanel.add(grid);
		infoPanel.add(view);
//The cancellation buttons	
		JPanel buttonPanel = new JPanel();
		JButton cancelRob1 = new JButton("Cancel Ricardo's order");
		JButton cancelRob2 = new JButton("Cancel NXT's order");
		JButton cancelRob3 = new JButton("Cancel Dave's order");
		JLabel totalReward = new JLabel("Total reward: " + model.getTotalReward());
		totalReward.setFont(new Font("Lucida Console", Font.BOLD + Font.ITALIC, 18));
		
		buttonPanel.add(cancelRob1);
		buttonPanel.add(cancelRob2);
		buttonPanel.add(cancelRob3);
		buttonPanel.add(totalReward);
		
//The title panel
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("<HTML><U>WAREHOUSE MANAGEMENT USER INTERFACE</U></HTML>");
		title.setFont(new Font("Lucida Console", Font.BOLD, 24));
		titlePanel.add(title);
		
		
		
		
		
		
//The master panel		
		setLayout(new BorderLayout());
		add(infoPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(titlePanel, BorderLayout.NORTH);
		
	}
	
	public void setRoute(LinkedList<Node> route, String name){
		grid.setRoute(route, name);
	}
	
	public void setRobCoord(String name,int x, int y) {
		grid.setRobCoord(name, x, y);
	}
}
