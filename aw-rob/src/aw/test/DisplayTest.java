package aw.test;

import aw.display.Display;

public class DisplayTest {
	public static void main(String[] args){
		Display display = new Display();
		//display.requestItem("Bananas", 3);
		//display.setLocation();
		Node current = new Node(6, 5);
		Node goal = new Node(5, 5);
		display.draw(current, goal, "Bananas", 3);
		current = new Node(5, 5);
		display.draw(current, goal, "Bananas", 3);
	}
}
