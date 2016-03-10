package aw.robotics;

import java.util.LinkedList;

import aw.comms.BluetoothCommandListener;
import aw.comms.CommandReceiver;
import aw.comms.CommandSender;
import aw.comms.Communication;
import aw.controller.MultiRobotController;
import aw.file.Item;
import aw.file.Job;
import aw.file.JobList;
import aw.test.Map;
import aw.test.Node;
import lejos.util.Delay;

/**
 * Abstraction of the NXT robot.
 * 
 * @author aranscope
 */
public class Robot implements BluetoothCommandListener, Runnable{
	private String name;
	private int x, y;
	private int angle;
	
	private CommandSender sender;
	
	private boolean running;
	private Map map;

	private boolean ready = true;
	public boolean requesting = false;
	
	private JobList jobList;
	
	/**
	 * Create a robot object to abstract communication with the NXT robots.
	 * @param name The name of the NXT robot.
	 * @param startX The starting X position of the robot on the grid.
	 * @param startY The starting Y position of the robot on the grid.
	 * @param angle The starting rotation of the robot
	 */
	public Robot(String name, int startX, int startY, int angle, JobList jobList){
		this.name = name;
		this.x = startX;
		this.y = startY;
		this.angle = angle;
		
		Communication.getRobotConnection(name).getCommandReceiver().addBluetoothCommandListener(this);

		this.sender = Communication.getRobotConnection(name).getCommandSender();
		this.running = true;
		map = new Map(8, 12);
		
		new Thread(this).start();
	}
	
	/**
	 * Set the job for the robot to complete.
	 * @param job Job for the robot to complete.
	 */
	private void setJob(Job job){
		Node current = new Node(this.x, this.y);

		for(Item item: job.getItems()){
			Node target = item.getPosition();
			LinkedList<Node> route = map.getPath(current, target);
			
			char[] moves = map.getMoves(route, angle).toCharArray();
			
			for(char c: moves){
				ready = false;
				sender.sendCommand("" + c);
				System.out.println(c);
				if(c == 'r') angle = (angle + 90) % 360;
				if(c == 'l') angle = angle > 0 ? angle - 90  : 270;
				if(c == 't') angle = (angle + 180) % 360;
				
				waitForAllRobotsReady();

			}
			
			ready = false;
			requesting = true;
			sender.sendCommand("i " + item + " " + item.getAmount());
			waitForAllRobotsReady();
			requesting = false;
			current = target;
		}
		
		Node target = job.getDropPoint();
		LinkedList<Node> route = map.getPath(current, target);
		
		char[] moves = map.getMoves(route, angle).toCharArray();
		
		for(char c: moves){
			ready = false;
			sender.sendCommand("" + c);
			System.out.println(c);
			if(c == 'r') angle = (angle + 90) % 360;
			if(c == 'l') angle = angle > 0 ? angle - 90  : 270;
			if(c == 't') angle = (angle + 180) % 360;
			
			waitForAllRobotsReady();
		}
		
		this.x = target.x;
		this.y = target.y;
	}
	
	public boolean isReady(){
		if(requesting) return true;
		else return ready;
	}
	
	public void waitForAllRobotsReady(){
		while(!MultiRobotController.ready()){
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	/**
	 * Set the x position of the robot.
	 * @param x x position of the robot.
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Set the y position of the robot.
	 * @param y y position of the robot.
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Get the x position of the robot.
	 * @return x position of the robot.
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Get the y position of the robot.
	 * @return y position of the robot.
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * Main robot thread.
	 */

	@Override
	public void commandReceived(String name, String command) {
		 ready = true;
	}

	@Override
	public void run() {
		while(running){
			try {
				Job job = jobList.getJob();
				setJob(job);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
