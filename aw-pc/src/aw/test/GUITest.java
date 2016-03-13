package aw.test;

import java.util.ArrayList;
import aw.GUI.GUI;
import aw.file.Job;

public class GUITest {

	public static void main(String[] args) {
		GUI guiTest = new GUI();
		Job job = new Job("10003,ah,1,bi,1");
		guiTest.setJob(job, "Ricardo");
		
		//Setting route for Ricardo
		Node p1 = new Node(0, 0);
		Node p2 = new Node(0, 1);
		Node p3 = new Node(1, 1);
		Node p4 = new Node(2, 1);
		Node p5 = new Node(3, 1);
		Node p6 = new Node(3, 2);
		Node p7 = new Node(3, 3);
		Node p8 = new Node(3, 4);
		ArrayList<Node> route = new ArrayList<Node>();
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
		Node p9 = new Node(0, 7);
		Node p10 = new Node(1, 7);
		Node p11 = new Node(2, 7);
		Node p12 = new Node(3, 7);
		Node p13 = new Node(4, 7);
		Node p14 = new Node(5, 7);
		Node p15 = new Node(5, 6);
		Node p16 = new Node(5, 5);
		ArrayList<Node> route2 = new ArrayList<Node>();
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
		Node p17 = new Node(11, 0);
		Node p18 = new Node(11, 1);
		Node p19 = new Node(11, 2);
		Node p20 = new Node(11, 3);
		Node p21 = new Node(11, 4);
		Node p22 = new Node(11, 5);
		Node p23 = new Node(11, 6);
		Node p24 = new Node(11, 7);
		ArrayList<Node> route3 = new ArrayList<Node>();
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
