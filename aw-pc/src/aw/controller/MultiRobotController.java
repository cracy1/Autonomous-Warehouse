package aw.controller;

import aw.robotics.Robot;

public class MultiRobotController implements Runnable{
	private Robot rob1;
	private Robot rob2;
	private Robot rob3;
	
	public MultiRobotController(){
		rob1 = new Robot("Ricardo", 0, 0, 0);
		//rob2 = new Robot();
		//rob3 = new Robot();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new Thread(new MultiRobotController()).start();
	}

	
}
