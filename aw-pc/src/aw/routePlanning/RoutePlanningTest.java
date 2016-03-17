package aw.routePlanning;

import java.util.LinkedList;

import sun.print.resources.serviceui;

public class RoutePlanningTest {
	private static Map map;
	
	public static void main(String[] args){
		SpaceAndTime spaceAndTime = new SpaceAndTime();
	map = new Map();
	
	CoopAStar robotOne = new CoopAStar(new Node(0,0), new Node(11,0),spaceAndTime, MapObstacles.ROBOTONE);
	CoopAStar robotTwo = new CoopAStar(new Node(0,1), new Node(2,1),spaceAndTime, MapObstacles.ROBOTTWO);
	 //AStar robotOne = new AStar(new Node (0,0),new Node (11,0), map ,MapObstacles.ROBOTONE);
	 //AStar robotTwo = new AStar(new Node (0,1),new Node (2,1), map ,MapObstacles.ROBOTTWO);
	
	 
	 LinkedList<Node> routeOne = robotOne.findRoute();
	 LinkedList<Node> routeTwo = robotTwo.findRoute();
	 System.out.println(routeOne);
	 System.out.println(map);
	 System.out.println("-----------------");
	 System.out.println(routeTwo);
//	 for (int x = 0; x < route.size(); x++) {
//		 System.out.println("move to :" + route.get(x) +", " + route.get(x));
//	 }
	}
	
}

