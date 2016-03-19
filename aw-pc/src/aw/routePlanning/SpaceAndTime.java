package aw.routePlanning;

import java.util.LinkedList;

public class SpaceAndTime {
	private LinkedList<Map> spaceAndTime;

	public SpaceAndTime(Node robotOne, Node robotTwo, Node robotThree) {

		spaceAndTime = new LinkedList<Map>();

		spaceAndTime.add(new Map(robotOne, robotTwo, robotThree));

	}

	public LinkedList<Map> getSpaceAndTime() {
		return spaceAndTime;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public Map getMap(int i) {
		while (i > spaceAndTime.size() - 1) {
			spaceAndTime.add(new Map());
		}

		return spaceAndTime.get(i);

	}

	/**
	 * when was the robots last timestamp (last move)
	 * 
	 * @param robot
	 * @return
	 */
	public int getLastMove(MapObstacles robot) {
		int timeStamp = spaceAndTime.size() - 1;
		if (timeStamp < 1) {
			return 0;

		}
		Map currentMap = spaceAndTime.get(timeStamp);

		while (timeStamp > 0 && !currentMap.checkMap(robot)) {
			timeStamp--;
			currentMap = spaceAndTime.get(timeStamp);
		}
		return timeStamp;

	}

	/**
	 * first thing is to find last map of that robot, add/edit the maps up to
	 * the end of the path
	 * 
	 * @param path
	 * @param robot
	 */
	public void addPath(LinkedList<Node> path, MapObstacles robot) {
		int lastMove = getLastMove(robot);
		int length = path.size();
		MapObstacles whichRobot = null;
		for (int i = lastMove; i < length + lastMove ; i++) {

			if (robot.equals(MapObstacles.ROBOTONE)) {
				whichRobot = MapObstacles.ROBOTONE;
			} else if (robot.equals(MapObstacles.ROBOTTWO)) {
				whichRobot = MapObstacles.ROBOTTWO;
			} else if (robot.equals(MapObstacles.ROBOTTHREE)) {
				whichRobot = MapObstacles.ROBOTTHREE;
			}

			this.getMap(i).update(path.removeLast(), whichRobot);

		}

	}

}
