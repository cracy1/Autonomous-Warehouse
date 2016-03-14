package aw.GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import aw.file.Job;
import aw.test.Node;

public class GUI {
	
	private Information info;
	private InformationModel model;
	private InformationComponent grid;
	
	public GUI() {
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info = new Information();
		model = new InformationModel(info);
		grid = new InformationComponent(model);
		frame.add(grid);

		
		frame.setVisible(true);
	}
	
	public void setJob(Job job, String name) {
		model.setJob(job, name);
	}
	
	public void setRoute(LinkedList<Node> route, String name){
		grid.setRoute(route, name);
	}
	
	public void setRobCoord(String name, int x, int y) {
		grid.setRobCoord(name, x, y);
	}

}
