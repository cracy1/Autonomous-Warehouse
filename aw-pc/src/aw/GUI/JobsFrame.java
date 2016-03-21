package aw.GUI;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aw.file.Job;


public class JobsFrame extends JFrame implements Observer{
	
	private JFrame frame;
	private InformationModel model;
	private Box box, box2;
	private Font titleFont;
	public JobsFrame(InformationModel model) {
		this.model = model;
		frame = new JFrame("Completed and upcoming jobs");
		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Boxes		
		box = Box.createVerticalBox();
		box2 = Box.createVerticalBox();
		
//Fonts
		this.titleFont = new Font("Times New Roman", Font.BOLD, 22);
//Master panel		
		JPanel comp = new JPanel();
		comp.setLayout(new GridLayout(0,2));
//sub-panel for the completed jobs list
		
		JPanel subPanel1 = new JPanel();
		subPanel1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		comp.add(subPanel1);
//sub-panel for the upcoming jobs list
		JPanel subPanel2 = new JPanel();
		subPanel2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		comp.add(subPanel2);
		
		JLabel title2 = new JLabel("Upcoming jobs: ");
		
		title2.setFont(titleFont);
		box2.add(title2);
		
		subPanel1.add(box);
		subPanel2.add(box2);
		
		for(int i = 0; i < model.getCompletedJobs().size(); i++) {
			Job job = model.getCompletedJobs().get(i);
			box.add(new JLabel("Job number " + job.getID() + ", with reward " + job.getJobReward() ));
		}
		
		frame.add(comp);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void update(Observable o, Object arg) {
		box.removeAll();
		
		
		JLabel title = new JLabel("Completed Jobs: ");
		box.add(title);
		for(int i = 0; i < model.getCompletedJobs().size(); i++) {
			Job job = model.getCompletedJobs().get(i);
			box.add(new JLabel("Job number " + job.getID()));
		}
		title.setFont(titleFont);
		
		box.revalidate();
		box.repaint();
		
		
	}

}
