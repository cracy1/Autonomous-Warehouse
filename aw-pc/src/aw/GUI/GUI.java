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
	private InformationComponent comp;
	private Grid grid;
	private JobList jobList;
	
	public GUI(JobList jobList) {
		this.jobList = jobList;
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		info = new Information();
		model = new InformationModel(info);
		
		grid = new Grid();
		
		comp = new InformationComponent(model, grid);
		comp.setOpaque(false);
		frame.add(comp);

//The second JFrame which contains the upcoming and completed jobs		
		JobsFrame jobsFrame = new JobsFrame(model, jobList);
		jobsFrame.getFrame().setVisible(true);
		model.addObserver(jobsFrame);

		
		frame.setVisible(true);
	}
	
	/**
	 * Wrapper method to set a job for a particular robot.
	 * @param job the job
	 * @param name the robot's name
	 */
	
	public void setJob(Job job, String name) {
		model.setJob(job, name);
	}
	
	/**
	 * Wrapper method to set the route for a particular robot
	 * @param route the route to be taken by the robot
	 * @param name the robot's name
	 */
	
	public void setRoute(LinkedList<Node> route, String name){
		LinkedList<Node> route2 = (LinkedList<Node>) route.clone();
		grid.setRoute(route2, name);
	}
	
	/**
	 * Wrapper method to initially set the robot's coordinates
	 * @param name the robot's name
	 * @param x the robot's x coordinate
	 * @param y the robot's y coordinate
	 */
	
	public void setRobCoord(String name, int x, int y) {
		grid.setRobCoord(name, x, y);
	}

}
