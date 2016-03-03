package aw.robotics;

import java.util.LinkedList;

import aw.comms.BluetoothCommandListener;
import aw.comms.CommandReceiver;
import aw.comms.CommandSender;
import aw.comms.Communication;
import aw.file.ItemList;
import aw.file.Job;
import aw.test.Map;
import aw.test.Node;

/**
 * Abstraction of the NXT robot.
 * 
 * @author aranscope
 */
public class Robot implements Runnable, BluetoothCommandListener{
	private String name;
	private int x, y;
	private int angle;
	
	private CommandSender sender;
	
	private boolean running;
	private Map map;
	
	private boolean moveComplete = true;
	
	/**
	 * Create a robot object to abstract communication with the NXT robots.
	 * @param name The name of the NXT robot.
	 * @param startX The starting X position of the robot on the grid.
	 * @param startY The starting Y position of the robot on the grid.
	 * @param angle The starting rotation of the robot
	 */
	public Robot(String name, int startX, int startY, int angle){
		this.name = name;
		this.x = startX;
		this.y = startY;
		this.angle = angle;
		Communication.addRobots();
		this.sender = Communication.getRobotConnection(name).getCommandSender();
		this.running = true;
		map = new Map(8, 12);	 
	}
	
	/**
	 * Set the job for the robot to complete.
	 * @param job Job for the robot to complete.
	 */
	public void setJob(Job job){
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
			
			LinkedList<Node> route = map.getPath(current, target);
		
			char[] moves = map.getMoves(route, angle).toCharArray();
			
			for(char c: moves){
				while(!moveComplete){
					try{
						Thread.sleep(50);
					}catch(InterruptedException e){}
				}
				moveComplete = false;
				sender.sendCommand("" + c);
				System.out.println(c);
				if(c == 'r') angle = (angle + 90) % 360;
				if(c == 'l') angle = angle > 0 ? angle - 90  : 270;
				if(c == 't') angle = (angle + 180) % 360;
			}
			
			sender.sendCommand("i " + item + " " + quantity);
			current = target;
		}
	}

	/**
	 * Set to route of the robot, as a list of nodes.
	 * @param route List of nodes.
	 */
	public void setRoute(LinkedList<Node> route){
		char[] moves = map.getMoves(route, angle).replace("t", "rr").toCharArray();
		
		for(char c: moves){
			if(c == 'r') angle = (angle + 90) % 360;
			if(c == 'l') angle = angle > 0 ? angle - 90  : 270;

			sender.sendCommand("" + c);
			
		}
		
		sender.sendCommand("i");
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
	public void run() {
		Thread robotInput = new Thread(){
			@Override
			public void run(){
				while(running){
					//String message = receiver.read();
				}
			}
		};
//		
//		robotInput.start();
//		while(running){
//			Job currentJob = jobList.getNext();
//			Item item;
//			while((item = currentJob.getNext()) != null){
//				Node robotPos = new Node(x, y);
//				Node targetPos = new Node(item.getX(), item.getY());
//				LinkedList<Node> route = map.getPath(robotPos, targetPos);
//				String moves = map.getMoves(route);
//				for(char move: moves){
//					sender.sendActionCommand(move);
//				}
//			}
//		}
	}

	@Override
	public void commandReceived(String name, String command) {
		 moveComplete = true;
		
	}
}
