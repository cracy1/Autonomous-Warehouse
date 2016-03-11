package aw.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import aw.file.Job;
import aw.test.Node;

public class GUI {
	
	private Information info;
	private InformationModel model;
	
	public GUI() {
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		info = new Information();
		model = new InformationModel(info);
		InformationComponent grid = new InformationComponent(model);
		frame.add(grid);
		setJob(new Job("10016,bi,2,ah,2,aj,1,ac,2,bb,3,af,4"), "Dave");

		
		frame.setVisible(true);
	}
	
	public void setJob(Job job, String name) {
		model.setJob(job, name);
	}

}
