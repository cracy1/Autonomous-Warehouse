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
		
		//Setting route for Ricardo
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(2, 1);
		Point p5 = new Point(3, 1);
		Point p6 = new Point(3, 2);
		Point p7 = new Point(3, 3);
		Point p8 = new Point(3, 4);
		ArrayList<Point> route = new ArrayList<Point>();
		route.add(p1);
		route.add(p2);
		route.add(p3);
		route.add(p4);
		route.add(p5);
		route.add(p6);
		route.add(p7);
		route.add(p8);
		guiTest.setRoute(route, "Ricardo");
		
		//Setting route for NXT
		Point p9 = new Point(0, 7);
		Point p10 = new Point(1, 7);
		Point p11 = new Point(2, 7);
		Point p12 = new Point(3, 7);
		Point p13 = new Point(4, 7);
		Point p14 = new Point(5, 7);
		Point p15 = new Point(5, 6);
		Point p16 = new Point(5, 5);
		ArrayList<Point> route2 = new ArrayList<Point>();
		route2.add(p9);
		route2.add(p10);
		route2.add(p11);
		route2.add(p12);
		route2.add(p13);
		route2.add(p14);
		route2.add(p15);
		route2.add(p16);
		guiTest.setRoute(route2, "NXT");
		
		//Setting route for The Other Robot
		Point p17 = new Point(11, 0);
		Point p18 = new Point(11, 1);
		Point p19 = new Point(11, 2);
		Point p20 = new Point(11, 3);
		Point p21 = new Point(11, 4);
		Point p22 = new Point(11, 5);
		Point p23 = new Point(11, 6);
		Point p24 = new Point(11, 7);
		ArrayList<Point> route3 = new ArrayList<Point>();
		route3.add(p17);
		route3.add(p18);
		route3.add(p19);
		route3.add(p20);
		route3.add(p21);
		route3.add(p22);
		route3.add(p23);
		route3.add(p24);
		guiTest.setRoute(route3, "TheOtherRobot");		
		
	}

}
