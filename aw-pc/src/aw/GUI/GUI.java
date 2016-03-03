package aw.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import aw.file.Job;

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

		
		frame.setVisible(true);
	}
	
	public void setJob(Job job) {
		model.setJob(job);
	}

}
