package aw.routePlanning;

import java.util.HashMap;
import java.util.LinkedList;

public class AStar {
	private int[][] heuristicMap;
	private int[][] movementCostMap;
	private int[][] totalCostMap;
	private boolean[][] walkable;

	private final int width = 12;
	private final int height = 8;

	private LinkedList<Node> openSet;
	private LinkedList<Node> closeSet;

	private HashMap<Node, Node> previousNodeMap;

	private LinkedList<Node> finalPath;

	public AStar(Node start, Node goal) {
		heuristicMap = new int[width][height];
		movementCostMap = new int[width][height]; // g score
		previousNodeMap = new HashMap<Node, Node>();
		totalCostMap = new int[width][height]; // f score
		walkable = new boolean[width][height];

		openSet = new LinkedList<Node>();
		closeSet = new LinkedList<Node>();
		// setting all default values
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				heuristicMap[x][y] = (int) (Math.abs(goal.getX() -x)
						+ Math.abs(goal.getY() - y));

				movementCostMap[x][y] = Integer.MAX_VALUE;

				totalCostMap[x][y] = Integer.MAX_VALUE;

				walkable[x][y] = true;

			}
		}
		// creating obstacles in the map
		for (int x = 1; x < width; x += 3) {
			for (int y = 1; y < 6; y++) {
			walkable[x][y] = false;
			closeSet.add(new Node(x,y));
			}
		}

		this.finalPath = findRoute(start, goal);
		
	}

	private LinkedList<Node> findRoute(Node start, Node goal) {
		// adding start node to the openSet
		openSet.add(start);
		Node currentNode;
	
		movementCostMap[start.getX()][start.getY()] = 0;
		while (!openSet.isEmpty()) {
		
			currentNode = getSmallestValue();
			
		
			openSet.remove(currentNode);
			closeSet.add(currentNode);
		
			
			if (currentNode.equals(goal)) {
				System.out.println("DONE");
				// return completed path
				return constructPath(currentNode);
			}

			for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
				for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
					if (x < width && x >= 0 && y >= 0 && y < height && (x == currentNode.getX() || y == currentNode.getY())) {
							
					
						
						Node neighbour = new Node(x, y);
					
						if (closeSet.contains(neighbour)) {
							// go to next loop
							continue;
						}
			
						int tentativeGScore = movementCostMap[currentNode.getX()][currentNode.getY()] + 1;
						if (!openSet.contains(neighbour)) {
							openSet.add(neighbour);
						} else if (tentativeGScore >= movementCostMap[neighbour.getX()][neighbour.getY()]) {
							// go to next loop
							continue;
						}
						previousNodeMap.put(neighbour, currentNode);
						movementCostMap[neighbour.getX()][neighbour.getY()] = tentativeGScore;
						totalCostMap[neighbour.getX()][neighbour.getY()] = tentativeGScore
								+ heuristicMap[neighbour.getX()][neighbour.getY()];
						
					}
				}
			}

		}
		// no path found
		new Exception("no path found");
		return null;

	}

	private LinkedList<Node> constructPath(Node currentNode) {
		LinkedList<Node> path = new LinkedList<>();
		path.add(currentNode);
		while (previousNodeMap.keySet().contains(currentNode)) {
			currentNode = previousNodeMap.get(currentNode);
			path.add(0, currentNode);
		}

		return path;
	}

	public LinkedList<Node> getPath() {
		return this.finalPath;
	}

	private Node getSmallestValue() {
		Node min;
		min = openSet.getFirst();
		for (Node n : openSet) {
			if (totalCostMap[n.getX()][ n.getY()] < totalCostMap[min.getX()][min.getY()]) {
				
				min = n;
			}
		}
		return min;
	}
}
