package aw.GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import aw.file.Job;
import aw.file.JobList;
import aw.test.Node;

public class GUI{
	
	private InformationComponent comp;
	private Grid grid;
	private JobList jobList;
	
	public GUI(InformationModel model) {
		this.jobList = new JobList();
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		grid = model.getGrid();
		
		comp = new InformationComponent(model, grid);
		comp.setOpaque(false);
		frame.add(comp);

//The second JFrame which contains the upcoming and completed jobs		
		JobsFrame jobsFrame = new JobsFrame(model, jobList);
		jobsFrame.getFrame().setVisible(true);
		model.addObserver(jobsFrame);

		
		frame.setVisible(true);
	}

}
