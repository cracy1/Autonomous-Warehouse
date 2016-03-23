package routePlanning;

public class Map {
	private int width = 12;
	private int height = 8;
	private MapObstacles[][] map;

	public Map(Node robotOneStart, Node robotTwoStart, Node robotThreeStart) {
		map = new MapObstacles[width][height];
		addObstacles();
		addRobots(robotOneStart, robotTwoStart, robotThreeStart);

	}


	private void addRobots(Node robotOneStart, Node robotTwoStart, Node robotThreeStart) {
		map[robotOneStart.getX()][robotOneStart.getY()] = MapObstacles.ROBOTONE;
		map[robotTwoStart.getX()][robotTwoStart.getY()] = MapObstacles.ROBOTTWO;
		map[robotThreeStart.getX()][robotThreeStart.getY()] = MapObstacles.ROBOTTHREE;
	}

	public void update(Node newNode, MapObstacles robot) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y] == robot) {
					this.map[x][y] = MapObstacles.EMPTY;
				}
			}
		}
		this.map[newNode.getX()][newNode.getY()] = robot;

	}

	public boolean checkIfThreeRobots() {
		boolean r1 = false;
		boolean r2 = false;
		boolean r3 = false;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (getMapObstacle(x, y).equals(MapObstacles.ROBOTONE)) {
					r1 = true;
				}
				if (getMapObstacle(x, y).equals(MapObstacles.ROBOTTWO)) {
					r2 = true;
				}
				if (getMapObstacle(x, y).equals(MapObstacles.ROBOTTHREE)) {
					r3 = true;
				}
			}
		}
		return r1 && r2 && r3;
		
	}

	private void addObstacles() {
		map = new MapObstacles[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x][y] = MapObstacles.EMPTY;
			}
		}

		for (int x = 5; x < 7; x++) {
			for (int y = 3; y < 5; y++) {
				map[x][y] = MapObstacles.OBSTACLE;
			}
		}
		for (int x = 2; x < 10; x += 7) {
			for (int y = 2; y < 6; y++) {
				map[x][y] = MapObstacles.OBSTACLE;
			}
		}

	}

	public Node findRobot(MapObstacles robot) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y] == robot) {
					return new Node(x, y);
				}
			}
		}
		return null;
	}

	public MapObstacles getMapObstacle(int x, int y) {
		return map[x][y];
	}

	public boolean equals(Map secondMap) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				if (!map[x][y].equals(secondMap.getMapObstacle(x, y))) {
					return false;
				}
			}
		}
		return true;
	}

	public Node getRobotPosition(MapObstacles robot) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y].equals(robot)) {
					return new Node(x, y);
				}
			}
		}
		return null;
	}

	private Map() {
		map = new MapObstacles[width][height];
	}

	public Map clone() {
		Map newMap = new Map();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				newMap.setMapObstactle(x, y, this.map[x][y]);
			}
		}
		return newMap;
	}

	private void setMapObstactle(int x, int y, MapObstacles obstacle) {
		this.map[x][y] = obstacle;

	}

	public String toString() {
		String output = "";

		for (int x = 0; x < width; x++) {
			output += "|";
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
				} else {
					output += "D";
				}
				output += "|";

			}
			output += "\n";
		}
		return output;

	}
}
