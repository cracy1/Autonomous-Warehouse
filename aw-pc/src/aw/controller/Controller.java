package aw.controller;

import java.util.LinkedList;

import aw.GUI.GUI;
import aw.comms.Communication;
import aw.robotics.Robot;

public abstract class Controller{
	public static LinkedList<Robot> robots;
	
	static{
		Communication.addRobots();
	}
	
	/**
	 * Add robots to the controller.
	 * @param robots
	 */
	Controller(Robot... robots){	
		for(Robot rob: robots){
			Controller.robots.add(rob);
		}
		
		run();
	}
	
	/**
	 * Used to synchronize the motion of robots. 
	 */
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
	
	abstract void run();
}
