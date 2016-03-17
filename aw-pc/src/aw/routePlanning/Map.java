package aw.routePlanning;

import java.util.LinkedList;

public class Map {
	public int width = 12;
	public int height = 8;
	private MapObstacles[][] map;

	public Map() {

		map = new MapObstacles[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x][y] = MapObstacles.EMPTY;
			}
		}
		for (int x = 1; x < width; x += 3) {
			for (int y = 1; y < 6; y++) {
				map[x][y] = MapObstacles.OBSTACLE;
			}
		}
	}

	public Map(Map map) {
		this.map = map.getMap();

	}

	public MapObstacles[][] getMap() {
		return map;
	}

	public MapObstacles getMapObstacle(int x, int y) {
		return map[x][y];
	}

	public void update(Node path, MapObstacles robot) {

		whichPath(path, robot);

	}

	public boolean equals(Map mapTwo){
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				if (!this.map[x][y].equals(mapTwo.getMapObstacle(x,y))){
					return false;
				}
			}
		}
		return true;
	}
	private void whichPath(Node path, MapObstacles robot) {
		MapObstacles whichRobot = null;

		if (robot.equals(MapObstacles.ROBOTONE)) {
			whichRobot = MapObstacles.ROBOTONE;
		} else if (robot.equals(MapObstacles.ROBOTTWO)) {
			whichRobot = MapObstacles.ROBOTTWO;
		} else if (robot.equals(MapObstacles.ROBOTTHREE)) {
			whichRobot = MapObstacles.ROBOTTHREE;
		}
		clearMap(whichRobot);
		addMoveToMap(path, whichRobot);

//		if (!pathOne.equals(null)) {
//			clearMap(MapObstacles.ROBOTONE);
//			addMoveToMap(pathOne, MapObstacles.ROBOTONE);
//		} else if (!pathTwo.equals(null)) {
//			clearMap(MapObstacles.ROBOTTWO);
//			addMoveToMap(pathTwo, MapObstacles.ROBOTTWO);
//		} else if (!pathThree.equals(null)) {
//			clearMap(MapObstacles.ROBOTTHREE);
//			addMoveToMap(pathThree, MapObstacles.ROBOTTHREE);
//		}
	}

	private void clearMap(MapObstacles oldRobotPath) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y].equals(oldRobotPath)) {
					map[x][y] = MapObstacles.EMPTY;
				}
			}
		}
	}

	private void addMoveToMap(Node path, MapObstacles robot) {

		map[path.getX()][path.getY()] = robot;

	}

	public boolean checkMap(MapObstacles robot) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y].equals(robot)) {
					return true;
				}
			}
		}
		return false;
	}
	public Node getRobotPosition(MapObstacles robot){
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y].equals(robot)) {
					return new Node(x,y);
				}
			}
		}
		return null;
	}
	public String toString() {
		String output = "";

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (this.getMapObstacle(x, y).equals(MapObstacles.EMPTY)) {
					output += " ";
				} else if (this.getMapObstacle(x, y).equals(MapObstacles.OBSTACLE)) {
					output += "-";
				} else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTONE)) {
					output += "1";
				} else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTTWO)) {
					output += "2";
				} else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTTHREE)) {
					output += "3";
				}
				output += "|";

			}
			output += "\n";
		}
		return output;

	}
}
