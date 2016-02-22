package aw.file.interfaces;

import aw.file.Job;
import aw.robotics.Robot;

public interface JobListInterface {
	public Job getJob(int x, int y); //x and y position of robot.
	public Job getJob(Robot rob); //robot object.
	public void sort();
}
