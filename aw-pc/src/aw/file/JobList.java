package aw.file;

import java.io.IOException;

import aw.file.interfaces.JobListInterface;
import aw.robotics.Robot;

public class JobList implements JobListInterface {

	String path = "/Users/Andrei/Documents/workspace/aw/Autonomous-Warehouse/aw-pc/res/";
	String jobs = path + "jobs.csv";
	String drops = path + "drops.csv";
	private double[] utility;

	private String[] job;

	public JobList() {

		try {
			ReadFile file = new ReadFile(jobs);
			job = file.OpenFile();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		utility = new double[job.length];
		for (int i = 0; i < job.length; i++) {
			Job job = new Job(getJob(i));
			utility[i] = job.getUtility();

		}

	}

	public int numberJobs() {
		return job.length;
	}

	public String getJob(int index) {
		return job[index];
	}

	@Override
	public Job setJob(Robot rob) {
		return null;
	}


	public void swap(double[] a, int x, int y) {
		double temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	public void swapString(String[] string, int x, int y) {
		String temp = string[x];
		string[x] = string[y];
		string[y] = temp;
	}

	private int partition(double[] a, String[] string, int left, int right) {
		int pivotIndex = (left + right) / 2;
		double pivot = a[pivotIndex];
		swap(a, pivotIndex, right);
		swapString(string, pivotIndex, right);
		int leftMark = left;
		int rightMark = right - 1;
		while (leftMark <= rightMark) {
			while (leftMark <= rightMark && a[leftMark] <= pivot)
				leftMark++;

			while (leftMark <= rightMark && a[rightMark] >= pivot)
				rightMark--;

			if (leftMark < rightMark) {
				swap(a, leftMark++, rightMark--);
				swapString(string, leftMark++, rightMark--);
			}
			swap(a, leftMark, right);
			swapString(string, leftMark, right);
		}
		return leftMark;

	}

	public void quickSort(double[] a, String[] string, int left, int right) {

		if (left < right) {
			int pivotIndex = partition(a, string, left, right);
			quickSort(a, string, left, pivotIndex);
			quickSort(a, string, pivotIndex + 1, right);
		}
	}

	// for testing purposes
	public void tell() {
		quickSort(utility, job, 0, 100);
		for (int i = 100; i >= 0; i--)
			System.out.println(utility[i] + " " + job[i]);
	}
	
}
