package aw.test;

import java.awt.Point;
import java.util.ArrayList;

import aw.GUI.GUI;
import aw.file.Job;

public class GUITest {

	public static void main(String[] args) {
		GUI guiTest = new GUI();
		Job job = new Job("10003,ah,1,bi,1");
		guiTest.setJob(job, "Ricardo");
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		ArrayList<Point> route = new ArrayList<Point>();
		route.add(p1);
		route.add(p2);
		route.add(p3);
		route.add(p4);
		guiTest.setRoute(route, "Ricardo");
	}

}
