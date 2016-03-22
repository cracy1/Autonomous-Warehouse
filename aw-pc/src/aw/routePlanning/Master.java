import java.util.ArrayList;
import java.util.HashMap;

public class Master {
	private HashMap<Integer, Map> maps;

	public Master(Node RobotOneStart, Node RobotTwoStart, Node RobotThreeStart) {
		maps = new HashMap<Integer, Map>();
	
		Map startMap = new Map(RobotOneStart, RobotTwoStart, RobotThreeStart);
		maps.put(0, startMap);
		
		CoopAStar test = new CoopAStar(new Node(0,5), 0 ,maps,MapObstacles.ROBOTONE);
		update(test);
		
		
	}
	public void addtoMaps(int key, Map map){
		maps.put(key, map);
	}
	public Map getMapAtTime(int key){
		return maps.get(key);
	}
	private void update(CoopAStar test){
		ArrayList<MapAndTimeStamp> path = test.getFinalPath();
		for (MapAndTimeStamp m: path){
			maps.put(m.getTimeStamp(), m.getMap());
			
		}
	}
}
