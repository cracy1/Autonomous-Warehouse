package routePlanning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
		if (start.equals(goal)){
			return new ArrayList<Node>();
			
		}
		while (!openSet.isEmpty()) {
			
			currentMapAndTime = getSmallest();
			if (currentMapAndTime.getfCost() > 50) {
				System.out.println("failure");
				return null;
			}

			openSet.remove(currentMapAndTime);
			closeSet.add(currentMapAndTime);

			if (currentMapAndTime.getMap().getMapObstacle(goal.getX(), goal.getY()).equals(robot)) {
				System.out.println("Path Found!");
				ArrayList<MapAndTimeStamp> path = reconstructRoute(currentMapAndTime);
				this.setFinalPath(path);

				openSet.clear();
			} else {

				ArrayList<MapAndTimeStamp> neighbours = findNeighbours(currentMapAndTime, robot);

				;
				for (MapAndTimeStamp n : neighbours) {

					if (!closeSet.contains(n)) {
						if (!openSet.contains(n)) {

							this.previousMap.put(n, currentMapAndTime);

							openSet.add(n);
						} else {

						}
					}
				}
			}

		}

		return null;

	}

	private ArrayList<MapAndTimeStamp> findNeighbours(MapAndTimeStamp currentMapAndTime, MapObstacles robot) {

		Node currentNode = currentMapAndTime.getMap().getRobotPosition(robot);
		ArrayList<MapAndTimeStamp> set = new ArrayList<MapAndTimeStamp>();

		if (!this.mapAtTime.containsKey((currentMapAndTime.getTimeStamp() + 1))) {

			this.mapAtTime.put(currentMapAndTime.getTimeStamp() + 1, currentMapAndTime.getMap());
		}
		ArrayList<Node> validMoveList = validMoves(currentMapAndTime.getTimeStamp(), currentNode);

		for (Node n : validMoveList) {
			Map newMap = this.mapAtTime.get(currentMapAndTime.getTimeStamp() + 1).clone();

			newMap.update(new Node(n.getX(), n.getY()), robot);

			Node oldNode = currentMapAndTime.getMap().getRobotPosition(robot);
			int newGCost = currentMapAndTime.getfCost() - heuristicTable[oldNode.getX()][oldNode.getY()] + 1;
			int newFCost = newGCost + heuristicTable[n.getX()][n.getY()];
			set.add(new MapAndTimeStamp(newMap, currentMapAndTime.getTimeStamp() + 1, newFCost));
		}

		return set;

	}

	private ArrayList<Node> validMoves(int timeStamp, Node currentNode) {
		ArrayList<Node> list = new ArrayList<Node>();

		for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
			for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
				if (x < width && x >= 0 && y >= 0 && y < height
						&& (x == currentNode.getX() || y == currentNode.getY())) {
					if (this.mapAtTime.get(timeStamp).getMapObstacle(x, y).equals(MapObstacles.EMPTY)) {
						if (this.mapAtTime.get(timeStamp + 1).getMapObstacle(x, y).equals(MapObstacles.EMPTY)) {
							list.add(new Node(x, y));
						}

					}
				}

			}
		}

		return list;

	}

	public ArrayList<MapAndTimeStamp> reconstructRoute(MapAndTimeStamp currentMapAndTimeStamp) {

		ArrayList<MapAndTimeStamp> path = new ArrayList<>();
		path.add(currentMapAndTimeStamp);

		while (this.previousMap.keySet().contains(currentMapAndTimeStamp)) {

			currentMapAndTimeStamp = previousMap.get(currentMapAndTimeStamp);
			if (!(currentMapAndTimeStamp == null)) {
				path.add(0, currentMapAndTimeStamp);
			}

		}

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
