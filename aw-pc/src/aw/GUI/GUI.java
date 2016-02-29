package aw.GUI;

import javax.swing.JFrame;

import aw.file.Job;

public class GUI {
	
	public GUI(Job job) {
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Information info = new Information(job);
		InformationComponent grid = new InformationComponent(info);
		frame.add(grid);

		
		frame.setVisible(true);
	}

}
