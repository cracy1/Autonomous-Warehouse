package aw.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import aw.file.Job;

public class GUI {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Warehouse Management User Interface");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Information info = new Information();
		InformationComponent grid = new InformationComponent(info);
		frame.add(grid);

		
		frame.setVisible(true);
	}

}
