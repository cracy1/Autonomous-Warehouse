package aw.controller;

import java.util.LinkedList;

import aw.file.Job;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;
import aw.test.Node;

/**
 * Run route and job allocation for a single robot.
 * 
 * @author aranscope
 */
public class SingleRobotDemoController {
	private Robot rob;
	private Map map;
	
	/**
	 * Initialise map, robot.
	 */
	public SingleRobotDemoController(){
		map = new Map(8, 12);
		rob = new Robot("Ricardo", 0, 3, 0);
		testJob();
		//testMultiRoute();
	}
	
	/**
	 * Run robot job allocation with a single job.
	 */
	public void testJob(){
		JobList jobList = new JobList();
		Job job = new Job(jobList.getJob(1));
		rob.setJob(job);
	}
	
	/**
	 * Run robot route allocation with a single route.
	 */
	public void testRoute(){
		LinkedList<Node> path = map.getPath(rob, new Node(5, 3));
		rob.setRoute(path);
	}
	
	/**
	 * Run robot route allocation with multiple routes.
	 */
	public void testMultiRoute(){
		LinkedList<Node> path = map.getPath(rob, new Node(2, 11));
		LinkedList<Node> path2 = map.getPath(rob, new Node(3, 6));
		LinkedList<Node> path3 = map.getPath(rob, new Node(5, 3));
		rob.setRoute(path);
		rob.setRoute(path2);
		rob.setRoute(path3);
	}
	
	public static void main(String[] args){
		new SingleRobotDemoController();
	}
	
}
