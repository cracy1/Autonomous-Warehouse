package aw.GUI;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import aw.file.ItemList;

/**
 * Class that displays the job information of each robot.
 * @author jon woodburn
 *
 */
public class InformationView extends JPanel implements Observer {
//Attributes	
	private InformationModel model;
	private JLabel jobId, totalReward, itemsInfoTitle;
	private ArrayList<JLabel> ricardoArray, nxtArray, daveArray;
	private ArrayList<ArrayList<JLabel>> masterArray;
	private Box box, box2, box3, box4, box5, box6;
	private final String robotName = "Ricardo";
	private final String robotName2 = "NXT";
	private final String robotName3 = "Dave";
	private Font titleFont, subFont;
	
	private Color green = new Color(34, 139, 34);
	private Color red = new Color(205, 0, 0);
	private Color blue = new Color(0, 154, 205);
	
	public InformationView(InformationModel model) {
		this.model = model;
		
		ricardoArray = new ArrayList<JLabel>();
		nxtArray = new ArrayList<JLabel>();
		daveArray = new ArrayList<JLabel>();
		
//Fonts
		this.titleFont = new Font("Times New Roman", Font.BOLD, 22);
		this.subFont = new Font("Courier", Font.BOLD + Font.ITALIC, 18);
				
//So that we can generalise the RoboInfo() method to all three robots,
//And not have an unnecessary amount of if statements
		masterArray = new ArrayList<ArrayList<JLabel>>();
		masterArray.add(ricardoArray);
		masterArray.add(nxtArray);
		masterArray.add(daveArray);
		
		RobotInfo(robotName, 1);
		RobotInfo(robotName2, 2);
		RobotInfo(robotName3, 3);
		
//Adds all our JLabels with a Box layout.		
		setLayout(new GridLayout(3, 1));

		box = Box.createVerticalBox();
		box2 = Box.createVerticalBox();
		box3 = Box.createVerticalBox();

		add(box);
		add(box2);
		add(box3);
		

				
		for(int i = 0; i< ricardoArray.size(); i++) {
			ricardoArray.get(i).setAlignmentX(LEFT_ALIGNMENT);
			box.add(ricardoArray.get(i));
		}
				
		for(int i = 0; i< nxtArray.size(); i++) {
			nxtArray.get(i).setAlignmentX(LEFT_ALIGNMENT);
			box2.add(nxtArray.get(i));
		}
				
		for(int i = 0; i < daveArray.size(); i++) {
			daveArray.get(i).setAlignmentX(LEFT_ALIGNMENT);
			box3.add(daveArray.get(i));
		}
		
	}
	
	/**
	 * Method that creates all the JLabels according to the robot
	 * @param name the robot's name
	 * @param robotNumber the robot's number
	 */
	
	public void RobotInfo(String name, int robotNumber) {


			if(model.getJob(name).isPresent()) {  //non-empty case
//Job information
				JLabel mainTitle = new JLabel("Robot " + robotNumber + ", " + name);

//Set colour of main title to the corresponding robot's colour			
				if(robotNumber ==1) {
					mainTitle.setForeground(red);
				}
				else if(robotNumber ==2) {
					mainTitle.setForeground(blue);
				}
				else {
					mainTitle.setForeground(green);
				}
				
				
				masterArray.get(robotNumber -1).add(mainTitle);
				mainTitle.setFont(titleFont);
				jobId = new JLabel("Job ID:   " + model.getJobId(name));
				totalReward = new JLabel("Total job reward:   " + String.valueOf(model.getJobReward(model.getJob(name).get())));
				itemsInfoTitle = new JLabel("|| Name  || reward  ||   weight   ||  coordinates");
				masterArray.get(robotNumber -1).add(jobId); 
				masterArray.get(robotNumber -1).add(totalReward);
				masterArray.get(robotNumber -1).add(itemsInfoTitle);

//Item information
				for(int i =0; i < model.numberItems(name); i++) {
					ItemList item = new ItemList();
					int j = item.getIndex(model.getJobItem(name, i));
					double reward = item.getReward(j);
					double weight = item.getWeight(j);
					int xCoord = item.getX(j);
					int yCoord = item.getY(j);
					masterArray.get(robotNumber -1).add(new JLabel("Item " + model.getJobItem(name, i) + ": " + "      " + reward + ";        " + weight + ";           " + "(" + xCoord + ", " + yCoord + ")"));
				}
			}
			else { //Empty case
				JLabel mainTitle = new JLabel("Robot " + robotNumber + ", " + name);
//Set colour of main title to the corresponding robot's colour
				
				if(robotNumber ==1) {
					mainTitle.setForeground(red);
				}
				else if(robotNumber ==2) {
					mainTitle.setForeground(blue);
				}
				else {
					mainTitle.setForeground(green);
				}
				
				masterArray.get(robotNumber -1).add(mainTitle);
				mainTitle.setFont(titleFont);
				jobId = new JLabel("Job ID:   ");
				totalReward = new JLabel("Total job reward:  ");
				itemsInfoTitle = new JLabel("|| Name  || reward  ||   weight   ||  coordinates");
				masterArray.get(robotNumber -1).add(jobId); 
				masterArray.get(robotNumber -1).add(totalReward);
				masterArray.get(robotNumber -1).add(itemsInfoTitle);
			}
			
	}
	
	/**
	 * Update method, as this class listens to InformationModel
	 */
	

	@Override
	public void update(Observable o, Object arg) {
		updateHelperMethod(arg, robNumber(arg));

	}
	
	/**
	 * Helper method to work out robot's number: 1 is Ricardo, 2 is NXt, 3 is Dave.
	 * @param name the robot's name
	 * @return the robot's corresponding number
	 */
	
	private int robNumber(Object name) {
		if(name.equals(robotName)) {
			return 1;
		}
		else if (name.equals(robotName2)) {
			return 2;
		}
		else {
			return 3;
		}
	}
	
	/**
	 * Helper method to update each JLabel correctly with the new job info. Called everytime a new job is set.
	 * @param name the rob's name
	 * @param robNumber the rob's number
	 */
	
	public void updateHelperMethod(Object name, int robNumber) {
		//Removes the previous items from the frame. 
					String name2 = (String) name;
					Container parent = masterArray.get(robNumber -1).get(0).getParent();
					masterArray.get(robNumber -1).get(1).setText("Job ID: " + model.getJobId(name2));
					masterArray.get(robNumber -1).get(2).setText("Total job reward:   " + String.valueOf(model.getJobReward(model.getJob(name2).get())));
					for(int i = masterArray.get(robNumber -1).size() -1; i >= 4; i--) {
						parent.remove(masterArray.get(robNumber -1).get(i));
						masterArray.get(robNumber -1).remove(masterArray.get(robNumber -1).size() -1);
						parent.revalidate();
						parent.repaint();
					}
			//Now adds the new item JLabels. 		
					for(int i =0; i < model.numberItems(name2); i++) {
						ItemList item = new ItemList();
						int j = item.getIndex(model.getJobItem(name2, i));
						double reward = item.getReward(j);
						double weight = item.getWeight(j);
						int xCoord = item.getX(j);
						int yCoord = item.getY(j);
						masterArray.get(robNumber -1).add(new JLabel("Item " + model.getJobItem(name2, i) + ": " + "      " + reward + ";        " + weight + ";           " + "(" + xCoord + ", " + yCoord + ")"));
					}
					
					for(int i = 4; i < masterArray.get(robNumber -1).size(); i++) {
						if(robNumber ==1) {
							box.add(masterArray.get(robNumber -1).get(i)); 
						}
						else if(robNumber ==2) {
							box2.add(masterArray.get(robNumber -1).get(i));  
						}
						else {
							box3.add(masterArray.get(robNumber -1).get(i));  
						}
						
					}
					
					parent.revalidate();
					parent.repaint();
				
					
	}
	
	/**
	 * Helper method to get Ricardo's JLabel array
	 * @return an ArrayList of JLabels
	 */
	
	public ArrayList<JLabel> getRicardoArray(){
		return ricardoArray;
	}
	
	/**
	 * Helper method to get NXT's JLabel array
	 * @return an ArrayList of JLabels
	 */
	
	public ArrayList<JLabel> getNxtArray(){
		return nxtArray;
	}
	
	/**
	 * Helper method to get Dave's JLabel array
	 * @return an ArrayList of JLabels
	 */
	
	public ArrayList<JLabel> getDaveArray(){
		return daveArray;
	}
}
