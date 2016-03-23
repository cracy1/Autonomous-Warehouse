package aw.controller;

import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;

public class SingleRobotFeedbackController extends Controller{

	public SingleRobotFeedbackController(){
		//super(new Robot("Ricardo", 0, 3, 0));
		super(new Robot("NXT", 0, 3, 0));
	}

	@Override
	public void run() {
		JobList jobList = new JobList();
		
		for(int i = 0; i < 100; i++){
			Job job = new Job(jobList.getJob(i));
			robots.getFirst().addJob(job);
		}
	}
	
	public static void main(String[] args){
		JobList jobList = new JobList();
		for(int i = 0; i < 100; i++){
			Job job = new Job(jobList.getJob(i));
			System.out.println(job);
			//robots.getFirst().addJob(job);
		}
//		Communication.addRobots();
//		new SingleRobotFeedbackController();
	}
}
