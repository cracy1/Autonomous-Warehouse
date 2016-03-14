package aw.GUI;


import java.util.Optional;

import aw.file.Job;

public class Information {
	
	private Optional<Job> jobRobot1, jobRobot2, jobRobot3;
	private int rewardCounter =0;
	
	public Information(){
		this.jobRobot1 = Optional.empty();
		this.jobRobot2 = Optional.empty();
		this.jobRobot3 = Optional.empty();
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
			this.jobRobot1 = Optional.of(job);
		}
		else if (robotName.equals("NXT")) {
			this.jobRobot2 = Optional.of(job);
		}
		else {
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
	
	public double getJobReward(String robotName) {
		if(robotName.equals("Ricardo")) {
			return Math.round(jobRobot1.get().getJobReward() * 100.0) /100.0;
		}
		else if (robotName.equals("NXT")) {
			return Math.round(jobRobot2.get().getJobReward() * 100.0) / 100.0;
		}
		else {
			return Math.round(jobRobot3.get().getJobReward() * 100.0) /100.0;
		}
		
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
	
	public void setTotalReward(double jobReward) {
		rewardCounter += jobReward;
	}
	
	public double getTotalReward() {
		return rewardCounter;
	}

}
