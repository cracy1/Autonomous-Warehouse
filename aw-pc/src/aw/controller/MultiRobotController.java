package aw.controller;

import aw.GUI.GUI;
import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;

public class MultiRobotController implements Runnable{
	private Robot rob1;
	private Robot rob2;
	private Robot rob3;
	private Map map;
	private GUI gui;
	
	public MultiRobotController(){
		gui = new GUI();
		map = new Map(8, 12);
		Communication.addRobots();
		rob1 = new Robot("Ricardo", 0, 3, 0, gui);
		rob2 = new Robot("NXT", 0, 5, 0, gui);
		gui.setRobCoord("Ricardo", 0, 3);
		gui.setRobCoord("NXT", 0, 5);
		testJob();
	}
	
	/**
	 * Run robot job allocation with a single job.
	 */
	public void testJob(){
		JobList jobList = new JobList();
		
		Job job1 = new Job(jobList.getJob(1));
		rob1.setJob(job1);
		gui.setJob(job1, "Ricardo");
		
		Job job2 = new Job(jobList.getJob(9));
		rob2.setJob(job2);
		gui.setJob(job2, "NXT");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new Thread(new MultiRobotController()).start();
	}

	
}
