package aw.routePlanning;

import java.awt.Point;
import java.util.ArrayList;

public class AStar {

	private int[][] heuristicMap;
	private int[][] movementCostMap;
	private Point[][] previousNodeMap;
	private int[][] totalCostMap;
	private int mapsize = 10;
	private ArrayList<Point> route;

	public AStar(int startX, int startY, int goalX, int goalY) {
		heuristicMap = new int[mapsize][mapsize];
		movementCostMap = new int[mapsize][mapsize];
		previousNodeMap = new Point[mapsize][mapsize];
		totalCostMap = new int[mapsize][mapsize];
		route = new ArrayList<Point>();
		for (int x = 0; x < mapsize; x++) {
			for (int y = 0; y < mapsize; y++) {
				heuristicMap[x][y] = Math.abs(goalX - x) + Math.abs(goalY - y);
			}
		}

		for (int x = 0; x < mapsize; x++) {
			for (int y = 0; y < mapsize; y++) {
				movementCostMap[x][y] = 1;
			}
		}

		for (int x = 0; x < mapsize; x++) {
			for (int y = 0; y < mapsize; y++) {
				previousNodeMap[x][y] = new Point(x, y);
			}
		}

		for (int x = 0; x < mapsize; x++) {
			for (int y = 0; y < mapsize; y++) {
				totalCostMap[x][y] = Integer.MAX_VALUE;
			}
		}

		route = findRoute(startX, startY, goalX, goalY);

	}

	private Point minimum(ArrayList<Point> openSet) {
		Point min = openSet.get(0);
		for (Point point : openSet) {
			if (totalCostMap[point.x][point.y] < totalCostMap[min.x][min.y]) {
				min = new Point(min.x, min.y);
			}
		}
		return min;
	}

	private ArrayList<Point> findRoute(int startX, int startY, int goalX, int goalY) {
		ArrayList<Point> openSet = new ArrayList<Point>();
		ArrayList<Point> closedSet = new ArrayList<Point>();
		openSet.add(new Point(startX, startY));
		movementCostMap[startX][startY] = 0;
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

					if ((x >= 0) && (y >= 0) && (x < mapsize) && (y < mapsize)
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

	private ArrayList<Point> path(int startX, int startY, int goalX, int goalY) {
		Point currentPoint = new Point(goalX, goalY);
		ArrayList<Point> path = new ArrayList<Point>();
		path.add(0, currentPoint);
		while (!previousNodeMap[currentPoint.x][currentPoint.y].equals(new Point(startX, startY))) {
			path.add(0, previousNodeMap[currentPoint.x][currentPoint.y]);
			currentPoint = new Point(previousNodeMap[currentPoint.x][currentPoint.y]);

		}
		path.add(0, new Point(startX, startY));
		return path;
	}

	public ArrayList<Point> getRoute() {
		return this.route;
	}
}
