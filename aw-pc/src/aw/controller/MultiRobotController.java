package aw.controller;

import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;

public class MultiRobotController implements Runnable{
	private static Robot rob1;
	private static Robot rob2;
	//private static Robot rob3;
	private Map map;
	
	public MultiRobotController(){
		map = new Map(8, 12);
		JobList jobList = new JobList();
		Communication.addRobots();
		rob1 = new Robot("Ricardo", 0, 3, 0, jobList);
		rob2 = new Robot("NXT", 3, 3, 0, jobList);
	}
	
	public static boolean ready(){
		return rob1.isReady() && rob2.isReady();// && rob3.isReady();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new Thread(new MultiRobotController()).start();
	}

	
}
