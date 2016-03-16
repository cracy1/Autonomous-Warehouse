package aw.routePlanning;

import java.util.LinkedList;

public class RoutePlanningTest {
	private static Map map;
	public static void main(String[] args){
	
	map = new Map();
	
	
	 AStar robotOne = new AStar(new Node (0,0),new Node (11,0), map ,MapObstacles.ROBOTONE);
	 AStar robotTwo = new AStar(new Node (0,1),new Node (2,1), map ,MapObstacles.ROBOTTWO);
	
	 
	 LinkedList<Node> routeOne = robotOne.getPath();
	 LinkedList<Node> routeTwo = robotTwo.getPath();
	 System.out.println(routeOne);
	 System.out.println(map);
	 System.out.println("-----------------");
	 System.out.println(routeTwo);
//	 for (int x = 0; x < route.size(); x++) {
//		 System.out.println("move to :" + route.get(x) +", " + route.get(x));
//	 }
	}
	
}

