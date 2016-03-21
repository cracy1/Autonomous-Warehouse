package aw.GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;

import aw.file.Job;

public class InformationModel extends Observable{
	private Information info;
	
	public InformationModel(Information info) {
		super();
		this.info = info;
	}
	
	public Optional<Job> getJob(String robotName) {
		return info.getJob(robotName);
	}
	
	public void setJob(Job job, String robotName){
		info.setJob(job, robotName);
		setChanged();
		notifyObservers(robotName);
	}
	
	public int getJobId(String robotName){
		return info.getJobID(robotName);
	}
	
	public String getJobItem(String robotName, int index) {
		return info.getJobItem(robotName, index);
	}
	
	public double getJobReward(Job job) {
		return info.getJobReward(job);
	}
	
	public int numberItems(String robotName){
		return info.numberItems(robotName);
	}
	
	public void setTotalReward(Job job) {
		info.setTotalReward(job);
	}
	
	public double getTotalReward() {
		return info.getTotalReward();
	}
	
	public ArrayList<Job> getCompletedJobs() {
		return info.getCompletedJobs();
	}
	

}
