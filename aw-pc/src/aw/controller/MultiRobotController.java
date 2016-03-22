package aw.controller;

import java.util.LinkedList;

import aw.GUI.GUI;
import aw.comms.Communication;
import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;

public class MultiRobotController {
	private static LinkedList<Robot> robots = new LinkedList<>();
	
	private Map map;
	private GUI gui;
	private JobList jobList;
	
	public MultiRobotController(){
		Communication.addRobots();
		
		jobList = new JobList();
		map = new Map(8, 12);
		gui = new GUI(jobList);
		
		robots.add(new Robot("Ricardo", 0, 3, 0, gui));
		gui.setRobCoord("Ricardo", 0, 3);
		robots.add(new Robot("NXT", 3, 3, 0, gui));
		gui.setRobCoord("NXT", 3, 3);
		//rob3 = new Robot("Dave", 3, 3, 0);
		allocateJobs();
		
	}
	
	public static void waitForRobotsReady(){
		boolean ready = true;
		do{
			ready = true;
			
			for(Robot r: robots) ready &= r.isReady();
			
			try{
				Thread.sleep(20);
			}catch(Exception e){}
		
		}while(!ready);
	}
	
	public void allocateJobs(){
		
		
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
