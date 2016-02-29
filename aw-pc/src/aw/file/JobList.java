package aw.file;

import java.io.IOException;

import aw.file.interfaces.JobListInterface;
import aw.robotics.Robot;

public class JobList implements JobListInterface {

	String path = "/home/students/add511/workspace/JobSelection/Autonomous-Warehouse/aw-pc/src/aw/file/";
	String jobs = path + "jobs.csv";
	double[] utility;

	private String[] job;

	public JobList() {
		

		try {
			ReadFile file = new ReadFile(jobs);
			job = file.OpenFile();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		utility = new double[job.length];
		for(int i = 0; i < job.length; i++){
			Job job = new Job(getJob(i));
			utility[i] = job.getUtility();
			
		}
		sort();

	}

	public String getJob(int index) {
		return job[index];
	}

	@Override
	public Job setJob(Robot rob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sort() {
		quickSort(utility, job, 0, job.length);
	}

	private int partition(double arr[], String arr2[], int left, int right) {
		int i = left, j = right;
		double tmp;
		String tmp2;
		double pivot = arr[(left + right) / 2];
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				
				tmp2 = arr2[i];
				arr2[i] = arr2[j];
				arr2[j] = tmp2;
				i++;
				j--;
			}
		}
		return i;

	}
	
	private void quickSort(double arr[], String arr2[], int left, int right) {
	      int index = partition(arr, arr2, left, right);
	      if (left < index - 1)
	            quickSort(arr, arr2, left, index - 1);
	      if (index < right)
	            quickSort(arr, arr2, index, right);

	}
}
