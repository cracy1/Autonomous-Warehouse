package aw.routePlanning;

import java.util.Currency;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

	public CoopAStar(Node start, Node goal, SpaceAndTime spaceAndTime, MapObstacles robot) {

		this.start = start;
		this.goal = goal;
		this.spaceAndTime = spaceAndTime;
		this.robot = robot;
		heuristicTable = new int[width][height];
		openSet = new LinkedList<TreeMap>();
		closeSet = new LinkedList<TreeMap>();
		
	}

	public LinkedList<Node> findRoute() {
		int timeStampCounter = spaceAndTime.getLastMove(this.robot);
		openSet.add(new TreeMap(spaceAndTime.getMap(timeStampCounter), timeStampCounter, 0,
				heuristicTable[start.getX()][start.getY()], null, this.robot));
		TreeMap currentTreeMap = null;
		while (!openSet.isEmpty()) {
			currentTreeMap = getSmallestFromOpenSet();
			openSet.remove(currentTreeMap);
			closeSet.add(currentTreeMap);

			if (currentTreeMap.getRobotPosition().equals(goal)) {
				return convertToNodeList(reconstructRoute(currentTreeMap));
			}
			Node currentNode = currentTreeMap.getRobotPosition();
			for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
				for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
					if (x < width && x >= 0 && y >= 0 && y < height
							&& (x == currentNode.getX() || y == currentNode.getY())) {
						int newTimeStamp = currentTreeMap.getTimeStamp() + 1;
						int newGCost = currentTreeMap.getgCost() + 1;
						int newFCost = newGCost + heuristicTable[x][y];

						Map newMap = spaceAndTime.getMap(newTimeStamp);

						TreeMap neighbour = new TreeMap(newMap, newTimeStamp, newGCost, newFCost, currentTreeMap,
								robot);

						if (!closeSet.contains(neighbour)) {
							if (!openSet.contains(neighbour)) {
								openSet.add(neighbour);
							}

						}

					}
				}
			}

		}
		// no path found
		new RuntimeException("found");
		return null;

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
			path.addLast(previousTreeMap);
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
			if (smallest.getfCost() > t.getfCost()) {
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
