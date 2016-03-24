package aw.routePlanning;

import java.awt.Point;
import java.util.ArrayList;

import aw.test.Node;

public class RoutePlanningTest {
	public static void main(String[] args){
		 SingleRobotAStar map = new SingleRobotAStar();
		 System.out.println(map.findRoute(new Node(0, 0), new Node(11, 7)));
	}
}
