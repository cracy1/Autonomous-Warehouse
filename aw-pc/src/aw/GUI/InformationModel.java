package aw.GUI;

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
		Optional<Job> oldJob = info.getJob(robotName);
		if(oldJob.isPresent()) {
			setTotalReward(oldJob.get().getJobReward());
		}
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
	
	public double getJobReward(String robotName) {
		return info.getJobReward(robotName);
	}
	
	public int numberItems(String robotName){
		return info.numberItems(robotName);
	}
	
	public void setTotalReward(double jobReward) {
		info.setTotalReward(jobReward);
	}
	
	public double getTotalReward() {
		return info.getTotalReward();
	}
	

}
