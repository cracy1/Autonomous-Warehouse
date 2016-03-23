package aw.GUI;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;

import aw.file.Job;
import aw.file.JobList;

/**
 * JFrame that displays the completed jobs and the upcoming jobs.
 * @author jon woodburn and dominic trott
 *
 */

public class JobsFrame extends JFrame implements Observer{
	
	private JFrame frame;
	private InformationModel model;
	private Box box, box2;
	private Font titleFont;
	private JobList upcomingJobs;
	private JList<Job> list;
	private JButton button;
	private JScrollPane scroll;
	private DefaultListModel<Job> listModel;
	private int oldArraySize;
	public JobsFrame(InformationModel model, JobList jobList) {
		this.model = model;
		this.upcomingJobs = jobList;
		oldArraySize = model.getCompletedJobs().size();
		frame = new JFrame("Completed and upcoming jobs");
		frame.setSize(900, 500);
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
		
		JLabel title = new JLabel("Completed Jobs: ");
		title.setFont(titleFont);
		box.add(title);
		
		for(int i = 0; i < model.getCompletedJobs().size(); i++) {
			Job job = model.getCompletedJobs().get(i);
			double rounded = Math.round(job.getJobReward() * 100.0) /100.0;
			box.add(new JLabel("Job number " + job.getID() + ", with reward " + rounded));
		}
		

//Creates a JList inside a JScroll.	
		listModel = new DefaultListModel<Job>();

		
		for(int i = 0; i< upcomingJobs.numberJobs(); i++) {
			Job job = new Job(upcomingJobs.getJob(i));
			listModel.addElement(job);
		}
		list = new JList<Job>(listModel);
		list.setOpaque(false);
		scroll = new JScrollPane(list);
		scroll.setOpaque(false);
		JScrollBar bar = scroll.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(10, 1000));
		
		button = new JButton("Cancel job");
//Everytime the button is clicked, remove the selected job from JobList.
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!list.isSelectionEmpty()) {
					int selected = list.getSelectedIndex();
					//upcomingJobs.remove(list.getSelectedIndex());
					listModel.remove(selected);
					list.revalidate();
					list.repaint();
				}
			}
		});
		
		box2.add(scroll);
		box2.add(button);
		frame.add(comp);
	}
	
	/**
	 * Method to get the JFrame
	 * @return the JFrame
	 */
	
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Class is observer of InformationModel, updates the completed jobs list everytime a new job is set.
	 */
	@Override
	public void update(Observable o, Object arg) {
		for(int i = oldArraySize; i < model.getCompletedJobs().size(); i++) {
			Job job = model.getCompletedJobs().get(i);
			box.add(new JLabel("Job number " + job.getID() + " with reward " + job.getJobReward()));
			for(int j = 0; j < upcomingJobs.numberJobs(); j++) {
				if(new Job(upcomingJobs.getJob(j)).getID() == job.getID()) {
					//upcomingJobs.remove(j);
					listModel.remove(j);
					list.revalidate();
					list.repaint();
				}
			}
			list.revalidate();
			list.repaint();
		}
		oldArraySize = model.getCompletedJobs().size();
		
		box.revalidate();
		box.repaint();
		
		
	}

}
