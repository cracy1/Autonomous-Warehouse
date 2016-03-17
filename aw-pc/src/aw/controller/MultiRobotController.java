package aw.controller;

import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;

public class MultiRobotController {
	private static Robot rob1;
	private static Robot rob2;
	//private static Robot rob3;
	private Map map;
	
	public MultiRobotController(){
		map = new Map(8, 12);
		Communication.addRobots();
		rob1 = new Robot("Ricardo", 0, 3, 0);
		rob2 = new Robot("NXT", 3, 3, 0);
		//rob3 = new Robot("Dave", 3, 3, 0);
		allocateJobs();
	}
	
	public void allocateJobs(){
		JobList jobList = new JobList();
		
		for(int i = 0; i < 100; i++){
			Job job = new Job(jobList.getJob(i));
			if(i % 3 == 0) rob1.addJob(job);
			else if(i % 2 == 0) rob2.addJob(job);
			//else rob3.addJob(job);
		}
	}
	
	public static boolean robotsReady(){
		return rob1.isReady() && rob2.isReady(); //&& rob3.isReady();
	}
	
	/**
	 * Run robot job allocation with a single job.
	 */
	public void testJob(){
		JobList jobList = new JobList();
		
		Job job1 = new Job(jobList.getJob(1));
		rob1.addJob(job1);
		
//		Job job2 = new Job(jobList.getJob(9));
//		rob2.setJob(job2);
	}
	

	public static void main(String[] args){
		new MultiRobotController();
	}

	
}
