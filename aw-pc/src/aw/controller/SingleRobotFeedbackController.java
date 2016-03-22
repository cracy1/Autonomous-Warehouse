package aw.controller;

import aw.GUI.GUI;
import aw.file.JobList;
import aw.robotics.Robot;
import aw.test.Map;

public class SingleRobotFeedbackController {
	private Robot rob;
	private Map map;
	private GUI gui;
	
	public SingleRobotFeedbackController(){
		map = new Map(8, 12);
		JobList list = new JobList();
		gui = new GUI(list);
		rob = new Robot("Ricardo", 0, 3, 0, gui);
	}
	
	public static void main(String[] args){
		new SingleRobotFeedbackController();
	}
}
