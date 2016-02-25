package aw.robotics;

import java.util.LinkedList;

import aw.comms.CommandReceiver;
import aw.comms.CommandSender;
import aw.comms.Communication;
import aw.file.Item;
import aw.file.Job;
import aw.test.Map;
import aw.test.Node;

public class Robot implements Runnable{
	private String name;
	private int x, y;
	private int angle;
	private CommandSender sender;
	private CommandReceiver receiver;
	private boolean running;
	private Map map;
	
	public Robot(String name, int startX, int startY, int angle){
		this.name = name;
		this.x = startX;
		this.y = startY;
		this.angle = angle;
		Communication.addRobots();
		this.sender = Communication.getCommandSender(name);
		this.running = true;
		map = new Map(8, 12);		 
	}
	
	public void setRoute(LinkedList<Node> route){
		char[] moves = map.getMoves(route, angle).replace("t", "rr").toCharArray();
		for(char c: moves){
			if(c == 'r') angle = (angle + 90) % 360;
			if(c == 'l') angle = angle > 0 ? angle - 90  : 270;

			sender.sendActionCommand(c);
		}
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

	@Override
	public void run() {
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
}
