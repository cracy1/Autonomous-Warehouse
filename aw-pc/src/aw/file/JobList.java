package aw.file;

import java.io.IOException;


import aw.file.interfaces.JobListInterface;
import aw.robotics.Robot;

public class JobList implements JobListInterface{
	
	String path = "/Users/Andrei/Desktop/Robot Programming/Warehouse files/";	
	String jobs = path + "jobs.csv";
	
	private String[] job;

	public JobList(){
		
		try {
			ReadFile file = new ReadFile(jobs);
			job = file.OpenFile();
			
			
		} catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	
	@Override
	public Job getJob(int i, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job setJob(Robot rob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
	
	public String getJob2(int index){
		return job[index];
	}

}
