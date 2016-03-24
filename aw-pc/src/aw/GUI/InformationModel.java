package aw.GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;

import aw.file.Job;
import aw.test.Node;

/**
 * Wrapper class of Information
 * @author jon woodburn
 *
 */
public class InformationModel extends Observable{
	private Information info;
	private Grid grid;
	
	public InformationModel(Information info) {
		super();
		this.info = info;
		this.grid = new Grid();
		setRobCoord("Ricardo", 0, 7);
		setRobCoord("Dave", 0, 0);
		setRobCoord("NXT", 7, 11);
		
	}
	
	/**
	 * Wrapper method of getJob()
	 * @param robotName the rob's name
	 * @return Optional of the robot's job
	 */
	
	public Optional<Job> getJob(String robotName) {
		return info.getJob(robotName);
	}
	
	/**
	 * Wrapper method of setJob()
	 * @param job the job to be set
	 * @param robotName the rob's name
	 */
	
	public void setJob(Job job, String robotName){
		info.setJob(job, robotName);
		setChanged();
		notifyObservers(robotName);
	}
	
	/**
	 * Wrapper method of getJobId()
	 * @param robotName the rob's name
	 * @return the job ID
	 */
	
	public int getJobId(String robotName){
		return info.getJobID(robotName);
	}
	
	/**
	 * Wrapper method to get a job's item
	 * @param robotName the rob's name
	 * @param index the item's index
	 * @return
	 */
	
	public String getJobItem(String robotName, int index) {
		return info.getJobItem(robotName, index);
	}
	
	/**
	 * Wrapper method to get the job's reward
	 * @param job the job in question
	 * @return the job's reward
	 */
	
	public double getJobReward(Job job) {
		return info.getJobReward(job);
	}
	
	/**
	 * Wrapper method to get the number of items in a job
	 * @param robotName the rob's name
	 * @return the number of items in a job
	 */
	
	public int numberItems(String robotName){
		return info.numberItems(robotName);
	}
	
	/**
	 * Wrapper method to set the total reward
	 * @param job the job in question
	 */
	
	public void setTotalReward(Job job) {
		info.setTotalReward(job);
	}
	/**
	 * Wrapper method to get the total reward
	 * @return the total reward
	 */
	
	public double getTotalReward() {
		return info.getTotalReward();
	}
	
	/**
	 * Wrapper method to get the list of completed jobs
	 * @return an ArrayList of completed jobs
	 */
	
	public ArrayList<Job> getCompletedJobs() {
		return info.getCompletedJobs();
	}
	
	public void setRoute(ArrayList<Node> route, String name) {
		grid.setRoute(route, name);
	}
	
	public void setRobCoord(String name, int x, int y) {
		grid.setRobCoord(name, x, y);
	}
	
	public Grid getGrid() {
		return grid;
	}
	

}
