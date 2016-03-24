package aw.controller;


import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.routePlanning.MapObstacles;
import aw.routePlanning.ReservationTable;
import aw.test.Node;

public class BetterMultiRobotController extends Controller{
	public static ReservationTable resTable;
	
	public BetterMultiRobotController(){
		super(new Robot("Dave", 0, 0, 0, MapObstacles.ROBOTONE), new Robot("NXT", 7, 11, 0, MapObstacles.ROBOTTWO), new Robot("Ricardo", 0, 7, 0, MapObstacles.ROBOTTHREE));
	}

	@Override
	void run() {
		resTable = new ReservationTable(new Node(robots.get(0).getX(), robots.get(0).getY()),
										new Node(robots.get(1).getX(), robots.get(1).getY()),
										new Node(robots.get(2).getX(), robots.get(2).getY()));
		
		
		
		JobList jobList = new JobList();
		for(int i = 0; i < 100; i++){
			Job job = new Job(jobList.getJob(i));
			if(i % 2 == 0) robots.get(0).addJob(job);
			else robots.get(1).addJob(job);
			//else rob3.addJob(job);
		}
	}	
	
	public static void main(String[] args){
		Communication.addRobots();
		new BetterMultiRobotController();
	}
}
