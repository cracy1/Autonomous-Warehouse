package aw.routePlanning;

import java.io.Console;
import java.util.Currency;
import java.util.LinkedList;
import java.util.PriorityQueue;

import lejos.nxt.addon.DIMUGyro.TemperatureUnits;

public class CoopAStar {
	private LinkedList<TreeMap> openSet;
	private LinkedList<TreeMap> closeSet;
	private LinkedList<TreeMap> finalMapRoute;
	private LinkedList<Node> finalNodeRoute;

	private SpaceAndTime spaceAndTime;
	private final Node start;
	private final Node goal;
	private final MapObstacles robot;
	private int[][] heuristicTable;
	private final int width = 12;
	private final int height = 8;
	private boolean found = false;

	public CoopAStar(Node goal, SpaceAndTime spaceAndTime, MapObstacles robot) {
		int lastMoveIndex = spaceAndTime.getLastMove(robot);
		this.start = spaceAndTime.getSpaceAndTime().get(lastMoveIndex).getRobotPosition(robot);

		this.goal = goal;
		this.spaceAndTime = spaceAndTime;
		this.robot = robot;
		heuristicTable = new int[width][height];
		fillHeuristics();
		openSet = new LinkedList<TreeMap>();
		closeSet = new LinkedList<TreeMap>();
		findRoute();

	}

	public void findRoute() {
		int timeStampCounter = spaceAndTime.getLastMove(this.robot);
		openSet.add(new TreeMap(spaceAndTime.getMap(timeStampCounter), timeStampCounter, 0,
				heuristicTable[start.getX()][start.getY()], null, this.robot));
		TreeMap currentTreeMap = null;
		while (!openSet.isEmpty()) {
			currentTreeMap = getSmallestFromOpenSet();

			openSet.remove(currentTreeMap);
			closeSet.add(currentTreeMap);

			if (currentTreeMap.getRobotPosition().equals(goal)) {
				System.out.println("Path found!");
				found = true;
				spaceAndTime.addPath(convertToNodeList(reconstructRoute(currentTreeMap)), robot);
				return;
			} else {
				Node currentNode = currentTreeMap.getRobotPosition();
				System.out.println(" current " + currentNode.getX() + " " + currentNode.getY());
				for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
					for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
						if (x < width && x >= 0 && y >= 0 && y < height
								&& (x == currentNode.getX() || y == currentNode.getY())) {
							int newTimeStamp = currentTreeMap.getTimeStamp() + 1;
							int newGCost = currentTreeMap.getgCost() + 1;
							int newFCost = newGCost + heuristicTable[x][y];
							Map newMap = new Map();
							for (int i = 0; i < width; i++) {
								for (int j = 0; j < height; j++) {
									newMap.setMapObstacle(i, j, spaceAndTime.getMap(newTimeStamp).getMapObstacle(i, j));
								}

							}

							if (window(currentTreeMap, 2, x, y)) {
								newMap.update(new Node(x, y), robot);

								TreeMap neighbour = new TreeMap(newMap, newTimeStamp, newGCost, newFCost,
										currentTreeMap, robot);

								if (!closeSetContains(neighbour)) {
									if (!openSetContains(neighbour)) {
										System.out.println(x + " " + y);
										openSet.add(neighbour);
									}

								}
							} else {
							

							}

						}
					}
				}
			}

		}
		if (found == false) {
			LinkedList<Node> path = new LinkedList<Node>();
			path.add(start);
			path.add(start);

			spaceAndTime.addPath(path, robot);
			new CoopAStar(goal, spaceAndTime, robot);

		}
		// no path found
		new RuntimeException("not found");

	}

	private boolean window(TreeMap currentTreeMap, int index, int x, int y) {
		int timestamp = currentTreeMap.getTimeStamp();
		for (int i = index; i > 0; i--){
			if (!spaceAndTime.getMap(i + timestamp).getMapObstacle(x, y).equals(MapObstacles.EMPTY)){
				return false;
			}
		}
	

		return true;

	}

	private boolean openSetContains(TreeMap neighbour) {
		for (TreeMap n : openSet) {
			if (n.equals(neighbour)) {

				return true;
			}
		}
		return false;
	}

	private boolean closeSetContains(TreeMap neighbour) {
		for (TreeMap n : closeSet) {
			if (n.equals(neighbour)) {

				return true;
			}
		}
		return false;
	}

	private int smallestGCost(TreeMap currentTreeMap) {
		int smallestGMap = 0;
		LinkedList<TreeMap> equalTreeMaps = new LinkedList<TreeMap>();
		for (TreeMap t : openSet) {
			if (t.equalMap(currentTreeMap)) {
				equalTreeMaps.add(t);
			}
		}
		for (TreeMap t : equalTreeMaps) {

			if (t.getgCost() < smallestGMap) {
				smallestGMap = t.getgCost();
			}
		}
		return smallestGMap;
	}

	private LinkedList<Node> convertToNodeList(LinkedList<TreeMap> treeMapRoute) {
		LinkedList<Node> path = new LinkedList<Node>();
		for (TreeMap t : treeMapRoute) {
			path.add(t.getRobotPosition());
		}

		return path;

	}

	private LinkedList<TreeMap> reconstructRoute(TreeMap currentTreeMap) {

		LinkedList<TreeMap> path = new LinkedList<TreeMap>();

		TreeMap previousTreeMap = currentTreeMap;

		path.add(previousTreeMap);
		while (previousTreeMap.getPreviousTreeMap() != null) {
			previousTreeMap = previousTreeMap.getPreviousTreeMap();

			path.add(previousTreeMap);
		}

		return path;

	}

	private void fillHeuristics() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				heuristicTable[x][y] = manhattan(new Node(x, y), this.goal);
			}
		}
	}

	private TreeMap getSmallestFromOpenSet() {
		TreeMap smallest = openSet.getFirst();

		for (TreeMap t : openSet) {

			if (smallest.getfCost() >= t.getfCost()) {
				smallest = t;
			}
		}

		return smallest;
	}

	private int manhattan(Node position, Node goal) {
		return Math.abs(goal.getX() - position.getX()) + Math.abs(goal.getY() - position.getY());
	}

	private LinkedList<Map> copySpaceAndTime() {
		return null;

	}
}
