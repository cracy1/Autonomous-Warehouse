package aw.GUI;


import java.util.ArrayList;
import java.util.Optional;

import aw.file.Job;

public class Information {
	
	private Optional<Job> jobRobot1, jobRobot2, jobRobot3;
	private double rewardCounter = 0;
	private ArrayList<Job> completedJobs;
	
	public Information(){
		this.jobRobot1 = Optional.empty();
		this.jobRobot2 = Optional.empty();
		this.jobRobot3 = Optional.empty();
		completedJobs = new ArrayList<Job>();
	}
	
	/**
	 * Gets the current job
	 * @param robotName the robot's name, to get the right job. 
	 * @return the current job depending on robot name
	 */
	
	public Optional<Job> getJob(String robotName) {
		if(robotName.equals("Ricardo")) {
			return jobRobot1;
		}
		else if(robotName.equals("NXT")){
			return jobRobot2;
		}
		else {
			return jobRobot3;
		}
		//Eventually, when we know all robot names, return jobRobot3.
	}
	
	/**
	 * Sets the jobRobot1 to a new one
	 * @param jobRobot1 the new jobRobot1
	 */
	
	public void setJob(Job job, String robotName){
		if(robotName.equals("Ricardo")) {
			if(jobRobot1.isPresent()) {
				completedJobs.add(jobRobot1.get());
				//setTotalReward(jobRobot1.get());
				
			}
			this.jobRobot1 = Optional.of(job);
		}
		else if (robotName.equals("NXT")) {
			if(jobRobot2.isPresent()) {
				completedJobs.add(jobRobot2.get());
				//setTotalReward(jobRobot2.get());
			}
			this.jobRobot2 = Optional.of(job);
		}
		else {
			if(jobRobot3.isPresent()) {
				completedJobs.add(jobRobot3.get());
				//setTotalReward(jobRobot3.get());
			}
			this.jobRobot3 = Optional.of(job);
		}
		
	}
	
	/**
	 * Gets the jobRobot1 ID, in order to display it
	 * @return the jobRobot1 ID as an int
	 */
	
	public int getJobID(String robotName){
		if(robotName.equals("Ricardo")) {
			return jobRobot1.get().getID();
		}
		else if (robotName.equals("NXT")) {
			return jobRobot2.get().getID();
		}
		else {
			return jobRobot3.get().getID();
		}
		
	}
	
	/**
	 * Gets the item at the given index
	 * @param index the index used
	 * @return the item as a string at the given index
	 */
	
	public String getJobItem(String robotName, int index) {
		if(robotName.equals("Ricardo")) {
			return jobRobot1.get().getItem(index);
		}
		else if (robotName.equals("NXT")) {
			return jobRobot2.get().getItem(index);
		}
		else {
			return jobRobot3.get().getItem(index);
		}
		
	}
	
	/**
	 * Get the job's total reward
	 * @param job the job in question
	 * @return the job's reward, rounded to two decimal places
	 */
	
	public double getJobReward(Job job) {

		return Math.round(job.getJobReward() * 100.0) /100.0;
		
	}
	
	/**
	 * Returns the number of items that are in the job.
	 * @return The total number of items in the job.
	 */
	
	public int numberItems(String robotName) {
		if(robotName.equals("Ricardo")) {
			return jobRobot1.get().numberItems();
		}
		else if (robotName.equals("NXT")) {
			return jobRobot2.get().numberItems();
		}
		else {
			return jobRobot3.get().numberItems();
		}
		
	}
	
	/**
	 * Sets the total reward
	 * @param job the job in question
	 */
	
	public void setTotalReward(Job job) {
		rewardCounter = rewardCounter + job.getJobReward();
	}
	
	/**
	 * gets the total reward
	 * @return the total reward
	 */
	
	public double getTotalReward() {
		return rewardCounter;
	}
	
	/**
	 * Method to get the list of completed jobs
	 * @return an ArrayList of completed jobs
	 */
	
	public ArrayList<Job> getCompletedJobs() {
		return completedJobs;
	}
 
}
