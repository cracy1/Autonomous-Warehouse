package aw.controller;

import java.util.LinkedList;
import aw.GUI.*;

import aw.GUI.GUI;
import aw.comms.Communication;
import aw.file.JobList;
import aw.robotics.Robot;

public abstract class Controller{
	public static LinkedList<Robot> robots = new LinkedList<>();
	public static int timeStamp = 0;
	
	/**
	 * Add robots to the controller, and initialise GUI.
	 * @param robots
	 */
	Controller(Robot... robots){	
		Information info = new Information();
		InformationModel model = new InformationModel(info);
		GUI gui = new GUI(model);
		for(Robot rob: robots){
			Controller.robots.add(rob);
			rob.setModel(model);
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
				Thread.sleep(50);
			}catch(Exception e){}
		
		}while(!ready);
		
		timeStamp++;
	}
	
	abstract void run();
}
