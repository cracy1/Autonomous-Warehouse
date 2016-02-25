package aw.routePlanning;

import java.awt.Point;
import java.util.ArrayList;

public class RoutePlanningTest {
	public static void main(String[] args){
	 AStar test = new AStar(0, 5 , 7, 5);
	 for (int y = 0; y < 8;y++){
		 for (int x = 0; x < 12; x++){
			 System.out.print(test.getWalkable(x, y)  + "|");
		 }
		 System.out.println();
		
	 }
	 
	 ArrayList<Point> route = test.getRoute();
	 System.out.println(route);
	 System.out.println(test.getWalkable(1, 5));
	 for (int x = 0; x < route.size(); x++) {
		 System.out.println("move to :" + route.get(x).x +", " + route.get(x).y);
	 }
	}
}
