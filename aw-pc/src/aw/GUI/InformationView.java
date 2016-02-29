package aw.GUI;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationView extends JPanel implements Observer {
	
	private InformationModel model;
	private JLabel title1, title2, title3, jobID, utility;
	private ArrayList<JLabel> itemsArray, itemsTitleArray;
	
	public InformationView(InformationModel model) {
		this.model = model;
//JLabels that stay the same: the titles.		
		title1 = new JLabel("Robot 1, Ricardo:");
		title2 = new JLabel("Job ID: ");
		title3 = new JLabel("Job utility: ");
		
//JLabels that change depending on job.		
		jobID = new JLabel(Integer.toString(model.getJobId()));
		utility = new JLabel(String.valueOf(model.getUtility()));
		
		itemsArray = new ArrayList<JLabel>();
		itemsTitleArray = new ArrayList<JLabel>();
		
		for(int i =0; i < model.numberItems(); i++){
			itemsArray.add(new JLabel(model.getJobItem(i)));
			itemsTitleArray.add(new JLabel("item " + i + ": ")); //unchanged titles again. 
		}
		
		Font titleFont = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 18);
		Font subFont = new Font("Times New Roman", Font.BOLD, 15);
//Unchanged titles.		
		title1.setFont(titleFont);
		title1.setLocation(750, 100);
		title2.setFont(subFont);
		title2.setLocation(750, 120);
		title3.setFont(subFont);
		title3.setLocation(750, 140);
		for(int i = 0; i < model.numberItems(); i++) {
			itemsTitleArray.get(i).setFont(subFont);
			itemsTitleArray.get(i).setLocation(750, 160 + i*20);
		}
		
//JLabels with changing values.
		jobID.setLocation(780, 120);
		utility.setLocation(780, 140);
		for(int i =0; i < model.numberItems(); i++) {
			itemsArray.get(i).setLocation(780, 160 + i*20);
		}
		
		add(title1);
		add(title2);
		add(title3);
		add(jobID);
		add(utility);
		for(int i =0; i < model.numberItems(); i++) {
			add(itemsTitleArray.get(i));
			add(itemsArray.get(i));
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		jobID.setText(Integer.toString(model.getJobId()));
		utility.setText(String.valueOf(model.getUtility()));
		for(int i =0; i < model.numberItems(); i++) {
			itemsArray.get(i).setText(model.getJobItem(i));
		}
		
	}

}
