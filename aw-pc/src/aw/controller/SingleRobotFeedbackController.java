package aw.controller;

import aw.robotics.Robot;
import aw.test.Map;

public class SingleRobotFeedbackController {
	private Robot rob;
	private Map map;
	
	public SingleRobotFeedbackController(){
		map = new Map(8, 12);
		rob = new Robot("Ricardo", 0, 3, 0);
	}
	
	public static void main(String[] args){
		new SingleRobotFeedbackController();
	}
}
