package aw.GUI;

import java.util.Observable;

import aw.file.Job;

public class InformationModel extends Observable{
	private Information info;
	
	public InformationModel(Information info) {
		super();
		this.info = info;
	}
	
	public Job getJob() {
		return info.getJob();
	}
	
	public void setJob(Job job){
		info.setJob(job);
		setChanged();
		notifyObservers();
	}
	
	public int getJobId(){
		return info.getJobID();
	}
	
	public String getJobItem(int index) {
		return info.getJobItem(index);
	}
	
	public double getUtility(){
		return info.getUtility();
	}
	
	public double getJobReward() {
		return info.getJobReward();
	}
	
	public int numberItems(){
		return info.numberItems();
	}
	
	

}
