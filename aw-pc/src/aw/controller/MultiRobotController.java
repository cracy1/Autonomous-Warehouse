package aw.controller;

import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;

public class MultiRobotController extends Controller{

	public MultiRobotController(){
		super(new Robot("Ricardo", 0, 3, 0), new Robot("NXT", 3, 3, 0));
	}

	@Override
	void run() {
		JobList jobList = new JobList();
		
		for(int i = 0; i < 100; i++){
			Job job = new Job(jobList.getJob(i));
			if(i % 2 == 0) robots.get(0).addJob(job);
			else robots.get(1).addJob(job);
			//else rob3.addJob(job);
		}
	}	
	
	public static void main(String[] args){
		new MultiRobotController();
	}
}
