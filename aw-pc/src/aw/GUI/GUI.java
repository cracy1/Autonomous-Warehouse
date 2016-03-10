package aw.GUI;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

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
		setJob(new Job("10016,bi,2,ah,2,aj,1,ac,2,bb,3,af,4"), "Dave");

		
		frame.setVisible(true);
	}
	
	public void setJob(Job job, String name) {
		model.setJob(job, name);
	}
	
	public void setRoute(ArrayList<Point> route, String name){
		grid.setRoute(route, name);
	}

}
