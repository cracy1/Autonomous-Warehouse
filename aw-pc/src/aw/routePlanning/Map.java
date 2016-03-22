
public class Map {
	private int width = 12;
	private int height = 8;
	private MapObstacles[][] map;
	
	public Map(Node robotOneStart, Node robotTwoStart, Node robotThreeStart ){
		map = new MapObstacles[width][height];
		addObstacles();
		addRobots(robotOneStart, robotTwoStart, robotThreeStart);
		
	}
	private void addRobots(Node robotOneStart, Node robotTwoStart, Node robotThreeStart ){
		map[robotOneStart.getX()][robotOneStart.getY()] = MapObstacles.ROBOTONE;
		map[robotTwoStart.getX()][robotTwoStart.getY()] = MapObstacles.ROBOTTWO;
		map[robotThreeStart.getX()][robotThreeStart.getY()] = MapObstacles.ROBOTTHREE;
	}
	private void addObstacles() {
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
	public Node findRobot(MapObstacles robot){
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y] == robot){
					return new Node(x,y);
				}
			}
		}
		return null;
	}
	
	
	public MapObstacles getMapObstacle(int x, int y) {
		return map[x][y];
	}
	public boolean equals(Map secondMap){
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y++) {
			
				
				if (!map[x][y].equals(secondMap.getMapObstacle(x,y))){
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
				}else{
					output += "D";
				}
				output += "|";

			}
			output += "\n";
		}
		return output;

	}
}
