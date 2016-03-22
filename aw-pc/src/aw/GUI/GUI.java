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
	
	private Information info;
	private InformationModel model;
	private InformationComponent grid;
	private JobList jobList;
	
	public GUI(JobList jobList) {
		this.jobList = jobList;
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		info = new Information();
		model = new InformationModel(info);
		grid = new InformationComponent(model);
		grid.setOpaque(false);
		frame.add(grid);
		
		JobsFrame jobsFrame = new JobsFrame(model, jobList);
		jobsFrame.getFrame().setVisible(true);
		model.addObserver(jobsFrame);

		
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
