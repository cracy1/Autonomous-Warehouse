package aw.test;

import aw.robot.Display;

public class DisplayTest {
	public static void main(String[] args){
		Display display = new Display();
		//display.requestItem("Bananas", 3);
		//display.setLocation();
		display.showPosition(0, 3, 5, 5);
	}
}
