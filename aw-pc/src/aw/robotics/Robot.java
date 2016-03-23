package aw.robotics;

import java.util.LinkedList;

import aw.GUI.GUI;
import aw.comms.BluetoothCommandListener;
import aw.comms.CommandReceiver;
import aw.comms.CommandSender;
import aw.comms.Communication;
import aw.controller.Controller;
import aw.controller.MultiRobotController;
import aw.file.Drop;
import aw.file.ItemList;
import aw.file.Job;
import aw.test.Map;
import aw.test.MultiRobotMap;
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
	//private MultiRobotMap map;
	private GUI gui;
	
	private boolean ready = true;
	private boolean requesting = false;
	
	private LinkedList<Job> jobs;
	private Drop dropPoints;

	
	/**
	 * Create a robot object to abstract communication with the NXT robots.
	 * @param name The name of the NXT robot.
	 * @param startX The starting X position of the robot on the grid.
	 * @param startY The starting Y position of the robot on the grid.
	 * @param angle The starting rotation of the robot
	 */
	public Robot(String name, int startX, int startY, int angle/*, GUI gui*/){
		this.name = name;
		this.x = startX;
		this.y = startY;
		this.angle = angle;
		this.dropPoints = new Drop();
		/*this.gui = gui;*/
		this.jobs = new LinkedList<>();
		this.map = new Map(8, 12);
		Communication.getRobotConnection(name).getCommandReceiver().addBluetoothCommandListener(this);

		this.sender = Communication.getRobotConnection(name).getCommandSender();
		this.running = true;
		//this.map = new Map(8, 12);
		
		new Thread(this).start();
		
	}
	
	public void addJob(Job job){
		jobs.add(job);
	}
	
	/**
	 * Set the job for the robot to complete.
	 * @param job Job for the robot to complete.
	 */
	private void executeJob(Job job){
		job.sort(this.x, this.y);
		int jobLength = job.numberItems();
		Node current = new Node(this.x, this.y);
		ItemList itemList = new ItemList();
		
		for(int i = 0; i < jobLength; i++){
			String item = job.getItem(i);
			int index = itemList.getIndex(item);
			int itemX = itemList.getX(index);
			int itemY = itemList.getY(index);
			int quantity = job.getQuantity(i);
	
			Node target = new Node(itemX, itemY);
			
			//LinkedList<Node> route = map.getPath(current, target, this);
			LinkedList<Node> route = map.getPath(current, target);
			char[] moves = map.getMoves(route, angle).toCharArray();
			
			for(char c: moves){
				ready = false;
				sender.sendCommand("" + c);
				if(c == 'r') angle = (angle + 90) % 360;
				if(c == 'l') angle = angle > 0 ? angle - 90  : 270;
				if(c == 't') angle = (angle + 180) % 360;
				//Controller.waitForRobotsReady();
				ready = false;
				//waitForResponse();
				Controller.waitForRobotsReady();
			}
			
			ready = false;
			requesting = true;
			sender.sendCommand("i " + item + " " + quantity);
			waitForResponse();
			requesting = false;
			current = target;
		}	
		
		
		
		/** Drop point stuff
		 * 
		 */
		dropPoints.sortClosestDropToRobot(this.x, this.y);
		int dx = dropPoints.getX(0);
		int dy = dropPoints.getY(0);
		Node dropNode = new Node(dx, dy);
		LinkedList<Node> route = map.getPath(current, dropNode);
		char[] moves = map.getMoves(route, angle).toCharArray();
		for(char c: moves){
			ready = false;
			sender.sendCommand("" + c);
			if(c == 'r') angle = (angle + 90) % 360;
			if(c == 'l') angle = angle > 0 ? angle - 90  : 270;
			if(c == 't') angle = (angle + 180) % 360;
			//waitForResponse();
			Controller.waitForRobotsReady();
		}
		
		
		
		
		
		requesting = true;
		/**
		 * Drop point logic.
		 */
		for(int i = 0; i < jobLength; i++){
			String item = job.getItem(i);
			int quantity = job.getQuantity(i);
			sender.sendCommand("d " + item + " " + quantity);
			Delay.msDelay(1000);
			
		}
		requesting = false;
		
		this.x = dropNode.x;
		this.y = dropNode.y;
		
	}
	
	public void waitForResponse(){
		while(!ready){
			Delay.msDelay(25);
		}
	}
	
	/**
	 * Get the name of the robot.
	 * @return
	 */
	public String getName(){
		return name;
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
	
	public LinkedList<Job> getJobs(){
		return jobs;
	}

	@Override
	public void run() {
		while(running){
			if(jobs.size() > 0){
				//gui.setJob(jobs.getFirst(), this.name);
				executeJob(jobs.removeFirst());
			}
		}
	}

	public boolean isReady() {
		return ready || requesting;
	}
}
