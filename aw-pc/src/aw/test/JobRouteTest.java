package aw.test;

import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;

public class JobRouteTest {
	public JobRouteTest(){
		Map m = new Map(8, 12);
		
		Robot rob = new Robot("Ricardo", 0, 3, 0);
		
		JobList jobList = new JobList();
		Job job = new Job(jobList.getJob2(0));
		rob.setJob(job);
		
	}

	public static void main(String[] args){
		new JobRouteTest();
	}
}
