package aw.GUI;

import javax.swing.JFrame;

public class GUI {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Information info = new Information();
		InformationComponent grid = new InformationComponent(info);
		frame.add(grid);
		
		frame.setVisible(true);
	}

}
