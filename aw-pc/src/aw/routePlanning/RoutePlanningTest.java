package aw.routePlanning;

import java.util.LinkedList;

public class RoutePlanningTest {
	public static void main(String[] args){
	
	 AStar test = new AStar(new Node (0,3),new Node (9,5));
	
	
	 
	 LinkedList<Node> route = test.getPath();
	 System.out.println(route);
	
//	 for (int x = 0; x < route.size(); x++) {
//		 System.out.println("move to :" + route.get(x) +", " + route.get(x));
//	 }
	}
}