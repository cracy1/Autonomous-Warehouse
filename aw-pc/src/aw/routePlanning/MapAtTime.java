package routePlanning;

import java.util.HashMap;

public class MapAtTime {
	public  HashMap<Integer, Map> maps;
	public MapAtTime(Node RobotOneStart, Node RobotTwoStart, Node RobotThreeStart) {
		maps = new HashMap<Integer, Map>();
		Map startMap = new Map(RobotOneStart, RobotTwoStart, RobotThreeStart);
		maps.put(0, startMap);
		
	}
	

}
