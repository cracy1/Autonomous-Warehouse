import java.util.ArrayList;

public class TestClass {

	public static void main(String[] args) {
		ReservationTable test = new ReservationTable(new Node(0,7), new Node(1,0), new Node(2,0));
	
		
		ArrayList<Node> route1 = test.findRoute(MapObstacles.ROBOTONE, new Node(0,0), 0);
		ArrayList<Node> route2 = test.findRoute(MapObstacles.ROBOTONE, new Node(4,0),0);
		ArrayList<Node> route3 = test.findRoute(MapObstacles.ROBOTONE, new Node(0,0), 0);
		
		ArrayList<Node> route4 = test.findRoute(MapObstacles.ROBOTONE, new Node(11,7),route1.size()-  1);
		ArrayList<Node> route5 = test.findRoute(MapObstacles.ROBOTTWO, new Node(10,7),route2.size() - 1);
		ArrayList<Node> route6 = test.findRoute(MapObstacles.ROBOTTHREE, new Node(9,7),route3.size() -1 );
		
		System.out.print(test.mapsToString());

		
	}

}
