package aw.test;

import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

/**
 * Class to convert map in rp.robotics.mapping.MapUtils to other formats.
 * @author aranscope
 *
 */
public class MapParser {
	private GridMap map;
	
	public MapParser(){
		//map = MapUtils.createRealWarehouse();
		map = MapUtils.createMarkingWarehouseMap();
	}
	
	/**
	 * Print the map to the console, '#' represents an obstacle, '.' represents a space.
	 */
	public void printMap(){
		for(int y = map.getYSize() - 1; y >= 0; y--){
			for(int x = 0; x < map.getXSize(); x++){
				System.out.print(map.isObstructed(x, y) ? " #": " .");
			}
			System.out.println();
		}
	}
	
	/**
	 * Get a 2D array of booleans where each cell represents either a space or
	 * an obstacle in the map. (i.e. true = space, false = obstacle).
	 * @return
	 */
	public boolean[][] getWalkableMap(){
		boolean[][] walkableMap = new boolean[map.getYSize()][map.getXSize()];
		
		for(int y = map.getYSize() - 1; y >= 0; y--){
			for(int x = 0; x < map.getXSize(); x++){
				walkableMap[y][x] = map.isObstructed(x,  y);
			}
		}
		
		return walkableMap;
	}
	
	public static void main(String[] args){
		new MapParser().printMap();
	}
}