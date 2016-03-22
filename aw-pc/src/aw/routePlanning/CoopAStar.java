import java.util.ArrayList;
import java.util.HashMap;

public class CoopAStar {

	private ArrayList<MapAndTimeStamp> openSet = new ArrayList<MapAndTimeStamp>();
	private ArrayList<MapAndTimeStamp> closeSet = new ArrayList<MapAndTimeStamp>();
	private int width = 12;
	private int height = 8;
	private HashMap<Integer, Map> mapAtTime = new HashMap<Integer, Map>();
	private HashMap<MapAndTimeStamp, MapAndTimeStamp> previousMap = new HashMap<MapAndTimeStamp, MapAndTimeStamp>();
	private ArrayList<MapAndTimeStamp> finalPath = new ArrayList<MapAndTimeStamp>();

	private int[][] heuristicTable = new int[width][height];

	public CoopAStar(Node goal, int startTime, HashMap<Integer, Map> mapAtTime, MapObstacles robot) {

		createTestingMap(mapAtTime);

		fillHeuristic(goal);

		Node start = mapAtTime.get(startTime).findRobot(robot);

		System.out.println(start);

		findRoute(start, goal, startTime, robot);

	}

	private void createTestingMap(HashMap<Integer, Map> mapAtTime) {
		for (int i = 0; i < mapAtTime.size(); i++) {
			this.mapAtTime.put(new Integer(i), mapAtTime.get(i));

		}
	}

	private ArrayList<Node> findRoute(Node start, Node goal, int startTime, MapObstacles robot) {
		MapAndTimeStamp firstMapAndTime = new MapAndTimeStamp(this.mapAtTime.get(startTime), startTime,
				heuristicTable[start.getX()][start.getY()]);
		previousMap.put(firstMapAndTime, null);

		openSet.add(firstMapAndTime);
		MapAndTimeStamp currentMapAndTime = null;
		while (!openSet.isEmpty()) {
			currentMapAndTime = getSmallest();
			System.out.println(openSet);
			openSet.remove(currentMapAndTime);
			closeSet.add(currentMapAndTime);

			if (currentMapAndTime.getMap().getMapObstacle(goal.getX(), goal.getY()).equals(robot)) {
				System.out.println("Path Found!");
				ArrayList<MapAndTimeStamp> path = reconstructRoute(currentMapAndTime);
				this.setFinalPath(path);
			} else {
				Node currentNode = currentMapAndTime.getMap().getRobotPosition(robot);
				for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
					for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
						
					}
				}

			}

		}

		return null;

	}

	public ArrayList<MapAndTimeStamp> reconstructRoute(MapAndTimeStamp currentMapAndTimeStamp) {

		ArrayList<MapAndTimeStamp> path = new ArrayList<>();
		path.add(currentMapAndTimeStamp);

		while (this.previousMap.keySet().contains(currentMapAndTimeStamp)) {
			currentMapAndTimeStamp = previousMap.get(currentMapAndTimeStamp);
			path.add(0, currentMapAndTimeStamp);

		}
		System.out.println(path.get(0).getMap());
		return path;

	}

	private MapAndTimeStamp getSmallest() {
		int smallest = openSet.get(0).getfCost();
		MapAndTimeStamp smallestMapAndTimeStamp = openSet.get(0);

		for (MapAndTimeStamp m : openSet) {
			if (m.getfCost() < smallest) {
				smallest = m.getfCost();
				smallestMapAndTimeStamp = m;
			}
		}
		return smallestMapAndTimeStamp;

	}

	private void fillHeuristic(Node goal) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				heuristicTable[x][y] = new AStar(new Node(x, y), goal).getPath().size() - 1;
			}
		}
	}

	/**
	 * @return the finalPath
	 */
	public ArrayList<MapAndTimeStamp> getFinalPath() {
		return finalPath;
	}

	/**
	 * @param finalPath
	 *            the finalPath to set
	 */
	public void setFinalPath(ArrayList<MapAndTimeStamp> finalPath) {
		this.finalPath = finalPath;
	}
}
