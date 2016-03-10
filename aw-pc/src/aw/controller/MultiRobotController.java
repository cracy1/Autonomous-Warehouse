package aw.controller;

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
	
	public MultiRobotController(){
		map = new Map(8, 12);
		Communication.addRobots();
		rob1 = new Robot("Ricardo", 0, 3, 0);
		rob2 = new Robot("NXT", 3, 3, 0);
		testJob();
	}
	
	/**
	 * Run robot job allocation with a single job.
	 */
	public void testJob(){
		JobList jobList = new JobList();
		
		Job job1 = new Job(jobList.getJob(1));
		rob1.setJob(job1);
		
//		Job job2 = new Job(jobList.getJob(9));
//		rob2.setJob(job2);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new Thread(new MultiRobotController()).start();
	}

	
}
