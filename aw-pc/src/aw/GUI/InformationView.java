package aw.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
	private JLabel jobId, totalReward, itemsInfoTitle;
	private Box box;
	
	public InformationView(InformationModel model) {
		this.model = model;
		
		RobotInfo();
		
	}
	
	public void RobotInfo() {
//Fonts
		Font titleFont = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 18);

//Job information
		itemsArray = new ArrayList<JLabel>();
		JLabel mainTitle = new JLabel("Robot 1, Ricardo");
		mainTitle.setForeground(Color.RED);
		itemsArray.add(mainTitle);
		mainTitle.setFont(titleFont);
		jobId = new JLabel("Job ID:   " + model.getJobId());
		totalReward = new JLabel("Total job reward:   " + String.valueOf(model.getJobReward()));
		itemsInfoTitle = new JLabel("||  Name  || reward  ||   weight   ||  coordinates  ||");
		itemsArray.add(jobId); 
		itemsArray.add(totalReward);
		itemsArray.add(itemsInfoTitle);

//Item information
		for(int i =0; i < model.numberItems(); i++) {
			ItemList item = new ItemList();
			int j = item.getIndex(model.getJobItem(i));
			double reward = item.getReward(j);
			double weight = item.getWeight(j);
			int xCoord = item.getX(j);
			int yCoord = item.getY(j);
			itemsArray.add(new JLabel("Item " + model.getJobItem(i) + ": " + "      " + reward + ";        " + weight + ";           " + "(" + xCoord + ", " + yCoord + ")"));
		}
		
//Adds all our JLabels with a Box layout.		
		box = Box.createVerticalBox();
		add(box);
		
		for(int i = 0; i< itemsArray.size(); i++) {
			itemsArray.get(i).setAlignmentX(LEFT_ALIGNMENT);
			box.add(itemsArray.get(i));
		}
	}
	
	public ArrayList<JLabel> getItemsArray() {
		return itemsArray;
	}

	@Override
	public void update(Observable o, Object arg) {
//Removes the previous items from the frame. 
			Container parent = itemsArray.get(0).getParent();
			itemsArray.get(1).setText("Job ID: " + model.getJobId());
			itemsArray.get(2).setText("Total job reward:   " + String.valueOf(model.getJobReward()));
			for(int i = itemsArray.size() -1; i >= 4; i--) {
				parent.remove(itemsArray.get(i));
				parent.revalidate();
				parent.repaint();
				itemsArray.remove(itemsArray.size() -1);
			}
	//Now adds the new item JLabels. 		
			for(int i =0; i < model.numberItems(); i++) {
				ItemList item = new ItemList();
				int j = item.getIndex(model.getJobItem(i));
				double reward = item.getReward(j);
				double weight = item.getWeight(j);
				int xCoord = item.getX(j);
				int yCoord = item.getY(j);
				itemsArray.add(new JLabel("Item " + model.getJobItem(i) + ": " + "      " + reward + ";        " + weight + ";           " + "(" + xCoord + ", " + yCoord + ")"));
			}
			
			for(int i = 4; i < itemsArray.size(); i++) {
				box.add(itemsArray.get(i));
			}

	}
}
