package aw.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import aw.file.ItemList;

public class InformationView extends JPanel implements Observer {
//Attributes	
	private InformationModel model;
	private ArrayList<JLabel> itemsArray;
	
	public InformationView(InformationModel model) {
		this.model = model;
		
		Font titleFont = new Font("Times New Roman", Font.BOLD, 18);
		itemsArray = new ArrayList<JLabel>();
		JLabel mainTitle = new JLabel("Robot 1, Ricardo");
		itemsArray.add(mainTitle);
		mainTitle.setFont(titleFont);
		JLabel jobId = new JLabel("Job ID:   " + model.getJobId());
		JLabel util = new JLabel("utility:   " + String.valueOf(model.getUtility()));
		itemsArray.add(jobId);
		itemsArray.add(util);
		
		for(int i =0; i < model.numberItems(); i++) {
			ItemList item = new ItemList();
			int j = item.getIndex(model.getJobItem(i));
			double reward = item.getReward(j);
			double weight = item.getWeight(j);
			int xCoord = item.getX(j);
			int yCoord = item.getY(j);
			itemsArray.add(new JLabel("Item " + (i+1) + ":   " + model.getJobItem(i) + "; " + reward + "; " + weight + "; " + "(" + xCoord + ", " + yCoord + ")"));
		}
		
		
		Box box = Box.createVerticalBox();
		add(box);
		
		for(int i = 0; i< itemsArray.size(); i++) {
			itemsArray.get(i).setAlignmentX(CENTER_ALIGNMENT);
			box.add(itemsArray.get(i));
		}
		
		
		
		

	}

	
	
/*

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

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		drawEverything(g2);
	}
	
*/
	@Override
	public void update(Observable o, Object arg) {
		//TODO: Implement update of the drawStrings. 
	}

}
