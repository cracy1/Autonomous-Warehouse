import java.awt.Point;
import java.util.ArrayList;

import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.MapUtils;

public class LRAStar {
	
	private static ArrayList<Point> route1;
	private static ArrayList<Point> route2;
	private static ArrayList<Point> route3;
	
	private static GridMap grid;
	private static int[][] map;

	public static void main(String[] args) {
		grid = MapUtils.createRealWarehouse();
		map = new int[grid.getYSize()][grid.getXSize()];
		
		makeNewMap();
		
		Point p = new Point(0, 5);
		Point g = new Point(3, 5);
		route1 = makeRoute(p, g);
		
		System.out.println(route1);
	}
	
	private void makeRoutes(Point current1, Point goal1, Point current2, Point goal2, Point current3, Point goal3){
		makeRoute(current1, goal1);
		makeRoute(current2, goal2);
		makeRoute(current3, goal3);
	}
	
	private static ArrayList<Point> makeRoute(Point current, Point goal){
		boolean routeComplete = false;
		int x = current.x;
		int y = current.y;
		int goalx = goal.x;
		int goaly = goal.y;
		
		if(!current.equals(goal)){
			if(y > goaly){
				if(y >= 0 && map[y-1][x] == 1){
					Point p = new Point(x, y-1);
					ArrayList<Point> route = makeRoute(p, goal);
					route.add(p);
					return route;
				}
				else if(y + 1 < map.length && map[y+1][x] == 1){
					Point p = new Point(x, y+1);
					ArrayList<Point> route = makeRoute(p, goal);
					route.add(p);
					return route;
				}
				else if(x > goalx){
					if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
				else if(x < goalx){
					if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
			}
			else if(y < goaly){
				if(y + 1 < map.length && map[y+1][x] == 1){
					Point p = new Point(x, y+1);
					ArrayList<Point> route = makeRoute(p, goal);
					route.add(p);
					return route;
				}
				else if(y >= 0 && map[y-1][x] == 1){
					Point p = new Point(x, y-1);
					ArrayList<Point> route = makeRoute(p, goal);
					route.add(p);
					return route;
				}
				else if(x > goalx){
					if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
				else if(x < goalx){
					if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
			}
			else{
				if(x > goalx){
					if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
				else if(x < goalx){
					if(x + 1 < map[y].length && map[y][x+1] == 1){
						Point p = new Point(x+1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
					else if(x >= 0 && map[y][x-1] == 1){
						Point p = new Point(x-1, y);
						ArrayList<Point> route = makeRoute(p, goal);
						route.add(p);
						return route;
					}
				}
				else{
					ArrayList<Point> route = new ArrayList<Point>();
					return route;
				}
			}
		}
		ArrayList<Point> route = new ArrayList<Point>();
		return route;
	}
	
	private static void makeNewMap(){
		for(int i = 0; i < grid.getYSize(); i++){
			for(int j = 0; j < grid.getXSize(); j++){
				if(grid.isObstructed(i, j)){
					map[i][j] = 100;
				}
				else{
					map[i][j] = 1;
				}
			}
		}
	}

}
