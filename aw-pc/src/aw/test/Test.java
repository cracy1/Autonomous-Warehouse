package aw.test;

import java.util.LinkedList;

import aw.robotics.Robot;

public class Test {
	public Test(){
		Map m = new Map(8, 12);
		
		Robot rob = new Robot("Ricardo", 0, 3, 0);
		
		LinkedList<Node> path = m.getPath(new Node(0, 3), new Node(6, 11));
		LinkedList<Node> path2 = m.getPath(new Node(6, 11), new Node(3, 6));
		LinkedList<Node> path3 = m.getPath(new Node(3, 6), new Node(5, 3));
		rob.setRoute(path);
		rob.setRoute(path2);
		rob.setRoute(path3);
		
		
	}

	public static void main(String[] args){
		new Test();
	}
}