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
	public Map(Map map){
		this.map = map.getMap();
		
	}
	public MapObstacles[][] getMap() {
		return map;
	}

	public MapObstacles getMapObstacle(int x, int y) {
		return map[x][y];
	}

	public void update(LinkedList<Node> pathOne, LinkedList<Node> pathTwo, LinkedList<Node> pathThree) {
	
		whichPath(pathOne, pathTwo, pathThree);

	}

	private void whichPath(LinkedList<Node> pathOne, LinkedList<Node> pathTwo, LinkedList<Node> pathThree) {
		if (!pathOne.isEmpty()) {
			clearMap(MapObstacles.ROBOTONE);
			addMoveToMap(pathOne, MapObstacles.ROBOTONE);
		} else if (!pathTwo.isEmpty()) {
			clearMap(MapObstacles.ROBOTTWO);
			addMoveToMap(pathTwo, MapObstacles.ROBOTTWO);
		} else if (!pathThree.isEmpty()) {
			clearMap(MapObstacles.ROBOTTHREE);
			addMoveToMap(pathThree, MapObstacles.ROBOTTHREE);
		}
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

	private void addMoveToMap(LinkedList<Node> path, MapObstacles robot) {
		Node n;
		for (int i = 0; i < path.size(); i++) {
			n = path.get(i);
			map[n.getX()][n.getY()] = robot;
		}

	}
	public String toString(){
		String output = "";
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (this.getMapObstacle(x, y).equals(MapObstacles.EMPTY)){
					output += " ";
				}else if (this.getMapObstacle(x, y).equals(MapObstacles.OBSTACLE)){
					output += "-";
				}else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTONE)){
					output += "1";
				}else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTTWO)){
					output += "2";
				}else if (this.getMapObstacle(x, y).equals(MapObstacles.ROBOTTHREE)){
					output += "3";
				}
				output += "|";
				
			}
			output += "\n";
		}
		return output;
		
	}
}
