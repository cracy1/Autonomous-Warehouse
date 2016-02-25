package aw.routePlanning;

import java.awt.Point;
import java.util.ArrayList;

public class RoutePlanningTest {
	public static void main(String[] args){
	 AStar test = new AStar(1, 0, 8, 9);
	 ArrayList<Point> route = test.getRoute();
	 
	 for (int x = 0; x < route.size(); x++) {
		 System.out.println("move to :" + route.get(x).x +", " + route.get(x).y);
	 }
	}
}
