package aw.routePlanning;

import java.util.LinkedList;

public class RoutePlanningTest {
	private static Map map;

	public static void main(String[] args) {
		Node robotOneStart = new Node(0, 0);
		Node robotOneGoal = new Node(0, 5);
		Node robotTwoStart = new Node(0, 3);
		Node robotTwoGoal = new Node(0,0);
		Node robotThreeStart = new Node(6, 1);
		Node robotThreeGoal = new Node(0, 4);
		
		SpaceAndTime spaceAndTime = new SpaceAndTime(robotOneStart, robotTwoStart, robotThreeStart);
	

		new CoopAStar(robotOneGoal, spaceAndTime, MapObstacles.ROBOTONE,0);
		new CoopAStar(robotTwoGoal, spaceAndTime, MapObstacles.ROBOTTWO,0);
		new CoopAStar(robotThreeGoal, spaceAndTime, MapObstacles.ROBOTTHREE,0);
//		robotOneGoal = new Node(11,7 );
//		robotTwoGoal = new Node(11,0);
//		robotThreeGoal = new Node(11,2);
//		new CoopAStar(robotOneGoal, spaceAndTime, MapObstacles.ROBOTONE);
//		new CoopAStar(robotTwoGoal, spaceAndTime, MapObstacles.ROBOTTWO);
//		new CoopAStar(robotThreeGoal, spaceAndTime, MapObstacles.ROBOTTHREE);
		 for (Map n : spaceAndTime.getSpaceAndTime()){
			 System.out.println(n);
			 
			 n.getRobotPosition(MapObstacles.ROBOTONE);
			 
		 }
		// for (int x = 0; x < route.size(); x++) {
		// System.out.println("move to :" + route.get(x) +", " + route.get(x));
		// }
	}

}
