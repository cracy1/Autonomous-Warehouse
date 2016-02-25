package aw.routePlanning;

import java.awt.Point;
import java.util.ArrayList;

public class AStar {

	private int[][] heuristicMap;
	private int[][] movementCostMap;
	private Point[][] previousNodeMap;
	private int[][] totalCostMap;
	private final int width = 12;
	private final int height = 8;
	private boolean[][] walkable;

	private ArrayList<Point> route;

	public AStar(int startX, int startY, int goalX, int goalY) {
		heuristicMap = new int[width][height];
		movementCostMap = new int[width][height];
		previousNodeMap = new Point[width][height];
		totalCostMap = new int[width][height];
		walkable = new boolean[width][height];
		route = new ArrayList<Point>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				heuristicMap[x][y] = Math.abs(goalX - x) + Math.abs(goalY - y);
				movementCostMap[x][y] = 1;
				previousNodeMap[x][y] = new Point(x, y);
				totalCostMap[x][y] = Integer.MAX_VALUE;
				walkable[x][y] = true;
			}
		}
		//NEED TO CHANGE IF MAP CHANGES!
		for (int x = 1; x <= width - 2; x += 3) {
			for (int y = 1; y <= height -2; y++) {
				walkable[x][y] = false;
			}
		}

		route = findRoute(startX, startY, goalX, goalY);

	}

	/**
	 * finds lowest closest point
	 * 
	 * @param openSet
	 *            the set we are testing against
	 * @return the lowest closest point
	 */
	private Point minimum(ArrayList<Point> openSet) {
		Point min = openSet.get(0);
		for (Point point : openSet) {
			if (totalCostMap[point.x][point.y] < totalCostMap[min.x][min.y] ) {
				min = new Point(min.x, min.y);
			}
		}
		
		return min;
	}

	/**
	 * finds route
	 * 
	 * @param startX
	 * @param startY
	 * @param goalX
	 * @param goalY
	 * @return path as an array list or returns null if it fails
	 */
	private ArrayList<Point> findRoute(int startX, int startY, int goalX, int goalY) {
		ArrayList<Point> openSet = new ArrayList<Point>();
		ArrayList<Point> closedSet = new ArrayList<Point>();
		openSet.add(new Point(startX, startY));
		movementCostMap[startX][startY] = 0;
		System.out.println(openSet);
		while (!openSet.isEmpty()) {
			Point currentPoint = minimum(openSet);
			openSet.remove(currentPoint);
			closedSet.add(currentPoint);

			if (currentPoint.equals(new Point(goalX, goalY))) {
				return path(startX, startY, goalX, goalY);
			}
			closedSet.add(currentPoint);
			for (int x = currentPoint.x - 1; x <= currentPoint.x + 1; x++) {
				for (int y = currentPoint.y - 1; y <= currentPoint.y + 1; y++) {

					if ((x >= 0) && (y >= 0) && (x < width) && (y < height)
							&& (x == currentPoint.x || y == currentPoint.y)) {

						Point ne = new Point(x, y);

						if (closedSet.contains(ne)) {
							continue;
						}
						int gScore = movementCostMap[currentPoint.x][currentPoint.y] + movementCostMap[ne.x][ne.y];
						if (!openSet.contains(ne)) {
							openSet.add(ne);
						} else if (gScore >= movementCostMap[ne.x][ne.y]) {
							continue;

						}
						previousNodeMap[ne.x][ne.y] = currentPoint;
						movementCostMap[ne.x][ne.y] = gScore;
						totalCostMap[ne.x][ne.y] = movementCostMap[ne.x][ne.y] + heuristicMap[ne.x][ne.y];

					}

				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param goalX
	 * @param goalY
	 * @return the path as an arraylist
	 */
	private ArrayList<Point> path(int startX, int startY, int goalX, int goalY) {
		Point currentPoint = new Point(goalX, goalY);
		ArrayList<Point> path = new ArrayList<Point>();
		path.add(0, currentPoint);
		// go backwards to get all nodes in the path
		while (!previousNodeMap[currentPoint.x][currentPoint.y].equals(new Point(startX, startY))) {
			path.add(0, previousNodeMap[currentPoint.x][currentPoint.y]);
			currentPoint = new Point(previousNodeMap[currentPoint.x][currentPoint.y]);

		}
		path.add(0, new Point(startX, startY));
		return path;
	}

	public boolean getWalkable(int x, int y) {
		return this.walkable[x][y];

	}

	public ArrayList<Point> getRoute() {
		return this.route;
	}
}
