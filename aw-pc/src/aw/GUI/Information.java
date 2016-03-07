package aw.GUI;


import aw.file.Job;

public class Information {
	
	private Job job;
	
	public Information(){
		this.job = new Job("10003,ah,1,bi,1");
	}
	
	/**
	 * Gets the current job
	 * @return the current job
	 */
	
	public Job getJob() {
		return job;
	}
	
	/**
	 * Sets the job to a new one
	 * @param job the new job
	 */
	
	public void setJob(Job job){
		this.job = job;
	}
	
	/**
	 * Gets the job ID, in order to display it
	 * @return the Job ID as an int
	 */
	
	public int getJobID(){
		return job.getID();
	}
	
	/**
	 * Gets the item at the given index
	 * @param index the index used
	 * @return the item as a string at the given index
	 */
	
	public String getJobItem(int index) {
		return job.getItem(index);
	}
	
	/**
	 * Gets the total utility of the job. 
	 * @return the utility
	 */
	
	public double getUtility() {
		return job.getUtility();
	}
	
	public double getJobReward() {
		return job.getJobReward();
	}
	
	/**
	 * Returns the number of items that are in the job. 
	 * @return The total number of items in the job
	 */
	
	public int numberItems() {
		return job.numberItems();
	}

}
