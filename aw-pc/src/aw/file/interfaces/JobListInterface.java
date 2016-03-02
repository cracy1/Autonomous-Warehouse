package aw.file.interfaces;

import aw.file.Job;
import aw.robotics.Robot;

public interface JobListInterface {
	public String getJob(int i); 
	public Job setJob(Robot rob); //robot object.
	public void sort();
}
