package routePlanning;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationTable {
	private HashMap<Integer, Map> maps;

	public ReservationTable(Node RobotOneStart, Node RobotTwoStart, Node RobotThreeStart) {
		maps = new HashMap<Integer, Map>();

		Map startMap = new Map(RobotOneStart, RobotTwoStart, RobotThreeStart);
		maps.put(0, startMap);
	}
	
	public ArrayList<Node> findRoute(MapObstacles robot, Node goal, int time){
		
		CoopAStar path = new CoopAStar(goal, time, maps, robot);
		
		update(path, time,robot);
		ArrayList<Node> finalPathListOfNode = new ArrayList<Node>();
		
		ArrayList<MapAndTimeStamp> finalPathListOfMapandTime = path.getFinalPath();
		for (MapAndTimeStamp m: finalPathListOfMapandTime){
			finalPathListOfNode.add(m.getMap().getRobotPosition(robot));
			
		}
		return finalPathListOfNode;
		
	}
	public int sizeOfMaps(){
		return maps.size();
	}
	public String mapsToString() {
		String output = "";
		for (int i: maps.keySet()){
			output += maps.get(i);
			output += "\n";
			
		}
		return output;
	}
	
	public void addtoMaps(int key, Map map) {
		maps.put(key, map);
	}
	
	public Map getMapAtTime(int key) {
		return maps.get(key);
	}
	
	private void update(CoopAStar test, int timeStart, MapObstacles robot) {
		
		ArrayList<MapAndTimeStamp> path = test.getFinalPath();
		int length = path.size();
		int endOfPath = timeStart + length;
		if (path == null || path.size() == 0){
			System.out.println("Test");
		}else{
			for (MapAndTimeStamp m : path) {
				maps.put(m.getTimeStamp(), m.getMap());

			}
			while(maps.containsKey(endOfPath)){
			
				Map newMap = maps.get(endOfPath).clone();
				newMap.update(maps.get(endOfPath -1).getRobotPosition(robot), robot);
				maps.put(endOfPath, newMap);
				endOfPath ++;
			}
		}
		
		
	}
}
