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
		// sort();

	}

	public int numberJobs() {
		return job.length;
	}

	public String getJob(int index) {
		return job[index];
	}

	@Override
	public Job setJob(Robot rob) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * private void quickSort(double [] a, String[] b, int left, int right) { if
	 * (left < right) { int pivotIndex = (left+right)/2; int pos = partition(a,
	 * b, left,right,pivotIndex); quickSort(a,b,left,pos-1);
	 * quickSort(a,b,pos+1,right); } }
	 * 
	 * private void swap(double[] a, int i, int j) { double t = a[i]; a[i] =
	 * a[j]; a[j] = t; }
	 * 
	 * private void swap2(String[] a, int i, int j){ String t = a[i]; a[i] =
	 * a[j]; a[j] = t; }
	 */

	/*
	 * private int partition(double [] a, String[] b, int left,int right,int
	 * pivotIndex) { swap(a, pivotIndex, right); swap2(b, pivotIndex, right);
	 * int pos = left;//represents boundary between small and large elements
	 * for(int i = left; i < right; i++) { if (a[i] < a[right]) { swap(a, i,
	 * pos); swap2(b, i, pos); pos++; } } swap(a, right, pos); swap2(b, right,
	 * pos); return pos; }
	 */

	public void swap(double[] a, int x, int y) {
		double temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	private int partition(double[] a, int left, int right) {
		int pivotindex = (left + right) / 2;
		double pivot = a[pivotindex];
		swap(a, pivotindex, right);
		int leftmark = left;
		int rightmark = right - 1;
		while (leftmark <= rightmark) {
			while (leftmark <= rightmark && a[leftmark] <= pivot)
				leftmark++;

			while (leftmark <= rightmark && a[rightmark] >= pivot)
				rightmark--;

			if (leftmark < rightmark)
				swap(a, leftmark++, rightmark--);
		}
		swap(a, leftmark, right);
		return leftmark;
	}

	public void quickSort(double[] a, int left, int right) {

		if (left < right) {
			int pivotindex = partition(a, left, right);
			quickSort(a, left, pivotindex);
			quickSort(a, pivotindex + 1, right);
		}
	}

	public void tell(){
		quickSort(utility, 0, 100);
		for(int i = 100; i >= 0; i--)
			System.out.println(utility[i]);
	}
	/*@Override
	public void sort() {
		quickSort(utility, 0, utility.length);
		for(int i = 0; i < utility.length; i++)
			System.out.println(utility[i]);

	}*/
}
