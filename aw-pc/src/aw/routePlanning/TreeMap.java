package aw.routePlanning;

import org.hamcrest.core.CombinableMatcher;
import org.jfree.data.ComparableObjectItem;

public final class TreeMap {
	private final Map map;
	private final int timeStamp;
	private final int fCost;
	private final int gCost;
	private final MapObstacles robot;
	private final TreeMap previousTreeMap;

	public TreeMap(Map map, int timeStamp, int gCost, int fCost, TreeMap previousTreeMap, MapObstacles robot) {
		this.map = map;

		this.timeStamp = timeStamp;
		this.fCost = fCost;
		this.robot = robot;
		this.gCost = gCost;

		this.previousTreeMap = previousTreeMap;
	
	}
	public TreeMap(Map map, int timeStamp, int gCost, int fCost, TreeMap previousTreeMap, MapObstacles robot,Node newPos) {
		this.map = map;

		this.timeStamp = timeStamp;
		this.fCost = fCost;
		this.robot = robot;
		this.gCost = gCost;
		
		this.previousTreeMap = previousTreeMap;
		this.map.getMap()[newPos.getX()][newPos.getY()] = robot;
		
	}

	/**
	 * gets this robots position in the map
	 * 
	 * @return the position of the robot
	 */
	public Node getRobotPosition() {

		return this.map.getRobotPosition(robot);
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return the timeStamp
	 */
	public int getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @return the fCost
	 */
	public int getfCost() {
		return fCost;
	}

	/**
	 * @return the gCost
	 */
	public int getgCost() {
		return gCost;
	}

	/**
	 * @return the previousMap
	 */
	public TreeMap getPreviousTreeMap() {
		return previousTreeMap;
	}

	public boolean equals(TreeMap treeMap) {
		if (this.map.equals(treeMap.map) ) {
			return true;
		} else {
			return false;
		}

	}

	public boolean equalMap(TreeMap treeMap) {
		if (this.map.equals(treeMap.map)) {
			return true;
		} else {
			return false;
		}

	}

}
