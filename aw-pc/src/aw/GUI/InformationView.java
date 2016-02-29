package aw.GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
<<<<<<< HEAD
import java.awt.Font;
=======

>>>>>>> 325cca58dbec075f8b481ad339013753bebac5de
import javax.swing.JLabel;
import javax.swing.JPanel;

import aw.file.Item;

public class InformationView extends JPanel implements Observer {
//Attributes	
	private InformationModel model;
	private String jobID, utility;
//	private ArrayList<JLabel> itemsArray;
	private ArrayList<String> itemsTitleArray, itemNames, itemCoord, itemReward, itemWeight;
	
	public InformationView(InformationModel model) {
		this.model = model;
		
//		itemsArray = new ArrayList<JLabel>();
		itemsTitleArray = new ArrayList<String>();
		itemNames = new ArrayList<String>();
		itemCoord = new ArrayList<String>();
		itemReward = new ArrayList<String>();
		itemWeight = new ArrayList<String>();

//Get the ID and utility of the current job.
		
		jobID = new String(Integer.toString(model.getJobId()));
		utility = new String(String.valueOf(Math.round(model.getUtility())));
		
//Unchanged titles
		for(int i =0; i < model.numberItems(); i++){
			itemNames.add(model.getJobItem(i));
			itemsTitleArray.add("Item " + (i+1) + ":          ");
		}
		
		
//Gets all the info of each item		
		for (int i = 0; i < model.numberItems(); i++) {
			Item item = new Item(itemNames.get(i));
			int j = item.getIndex(itemNames.get(i));
			double reward = item.getReward(j);
			double weight = item.getWeight(j);
			int xCoord = item.getX(j);
			int yCoord = item.getY(j);
			itemCoord.add("(" + Integer.toString(xCoord) + ", " + Integer.toString(yCoord) + ")");
			itemReward.add(String.valueOf(reward));
			itemWeight.add(String.valueOf(weight));

		}
	}
	
	/**
	 * Draws everything on the JPanel. JLabels would be better however. 
	 * @param g2 the Graphics2D object. 
	 */
	public void drawEverything(Graphics2D g2) {
		Font titleFont = new Font("Times New Roman", Font.BOLD, 18);
		Font subFont = new Font("Times New Roman", Font.BOLD, 13);
		g2.setFont(titleFont);
		g2.drawString("Robot 1, Ricardo:", 750, 40);
		g2.setFont(subFont);
		g2.drawString("Job ID:          " + jobID, 750, 60);
		g2.drawString("Job utility:     " + utility, 750, 80);
		for(int i = 0; i < itemNames.size(); i++) {
			g2.drawString(itemsTitleArray.get(i) + itemNames.get(i) + "; " + itemCoord.get(i) + "; " + itemReward.get(i) + "; " + itemWeight.get(i), 750, 100 + i*20);
		}
	}
	/**
	 * Calls drawEverything to draw the appropriate info on the JPanel.
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		drawEverything(g2);
	}
	
	/**
	 * updates the canvas with the new information.
	 */

	@Override
	public void update(Observable o, Object arg) {
		//TODO: Implement update of the drawStrings. 
	}

}
