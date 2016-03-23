package routePlanning;

import java.util.ArrayList;
import java.util.Random;

import javax.security.auth.login.CredentialException;

public class TestClass {

	public static void main(String[] args) {
		ReservationTable test = new ReservationTable(new Node(0, 0), new Node(1, 0), new Node(2, 0));

		int robotOneLastTimeStamp = 0;
		int robotTwoLastTimeStamp = 0;
		int robotThreeLastTimeStamp = 0;
		Node n1 = null;
		Node n2 = null;
		Node n3 = null;
		for (int i = 0; i < 100; i++){
			 n1 = createValidGoal(n2, n3);
			 n2 = createValidGoal(n1, n3);
			 n3 = createValidGoal(n1, n2);
			System.out.println(n1);
			System.out.println(n2);
			System.out.println(n3);
			ArrayList<Node> route1 = test.findRoute(MapObstacles.ROBOTONE, n1, robotOneLastTimeStamp);
			ArrayList<Node> route2 = test.findRoute(MapObstacles.ROBOTTWO, n2, robotTwoLastTimeStamp);
			ArrayList<Node> route3 = test.findRoute(MapObstacles.ROBOTTHREE, n3, robotThreeLastTimeStamp);
			robotOneLastTimeStamp += route1.size() -1;
			robotTwoLastTimeStamp += route2.size()  -1;
			robotThreeLastTimeStamp += route3.size() -1;
			
		}
		
		for (int i = 0 ; i < test.sizeOfMaps(); i++){
			System.out.println(test.getMapAtTime(i).checkIfThreeRobots());
			System.out.println(test.getMapAtTime(i));
			
			
		}
		
	//	System.out.print(test.mapsToString());

	}

	private static Node createValidGoal(Node goalOne, Node goalTwo) {
		Random r = new Random();
		Node n = null;
		MapObstacles[][] check = new MapObstacles[12][8];
		
		
		ArrayList<Node> check1 = new ArrayList<Node>();
		check1.add(new Node(2,2));
		check1.add(new Node(2,3));
		check1.add(new Node(2,4));
		check1.add(new Node(2,5));
		
		check1.add(new Node(5,3));
		check1.add(new Node(5,4));
		
		check1.add(new Node(6,3));
		check1.add(new Node(6,4));
		
		check1.add(new Node(9,2));
		check1.add(new Node(9,3));
		check1.add(new Node(9,4));
		check1.add(new Node(9,5));
		
		int x = r.nextInt(12);
		int y = r.nextInt(8);
		n = new Node(x, y);
		if (goalOne == null){
			goalOne = new Node(-1,-1);
		}
		if (goalTwo == null){
			goalTwo = new Node(-1,-1);
		}
	
		while (n.equals(goalOne) || n.equals(goalTwo) || check1.contains(n)) {
			 x = r.nextInt(12);
			 y = r.nextInt(8);
			n = new Node(x, y);
		}
		return n;
		
	}
}
 