package aw.file;

import java.io.IOException;

import aw.file.interfaces.JobListInterface;

public class JobList implements JobListInterface {

	FilesPath pathForFiles = new FilesPath();
	String jobs = pathForFiles.path + "jobs.csv";
	String drops = pathForFiles.path + "drops.csv";

	final int numberOfJobs = 200;	// represents the number of jobs we want to work with
	private String[] job;
	private double[] utility;


	/*
	 *  The constructor reads the file containing the job list, then initialises and builds arrays that contain information about 
	 *  each job in the list. The index corresponds to one unique job, and this index is common through both arrays.
	 */
	public JobList() {

		try {
			ReadFile file = new ReadFile(jobs);
			job = file.OpenFile();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		utility = new double[numberJobs()];
		for (int i = 0; i < numberJobs(); i++) {
			Job job = new Job(getJob(i));
			utility[i] = job.getUtility();

		}
		quickSort(utility, job, 0, numberJobs());

	}

	/*
	 * Gets the number of jobs in the array.
	 * @return the number of jobs in the array
	 */
	public int numberJobs() {
		return numberOfJobs;
	}
	
	/*
	 * Gets the job at the given index
	 * @param index the index of the job
	 * @return the job at the given index
	 */
	public String getJob(int index) {
		return job[index];
	}

	/*
	 * Swaps the position of 2 elements in the given array of doubles.
	 * @param a the array in which elements are to be swapped
	 * @param x the index of the first element
	 * @param y the index of the second element
	 */
	private void swap(double[] a, int x, int y) {
		double temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	/*
	 * Swaps the position of 2 elements in a given array of strings.
	 * @param a the array in which elements are to be swapped
	 * @param x the index of the first element
	 * @param y the index of the second element
	 */
	private void swapString(String[] string, int x, int y) {
		String temp = string[x];
		string[x] = string[y];
		string[y] = temp;
	}

	/*
	 * Partitions the array of type double in a way where the items smaller than the pivot are placed to the left of the pivot and the items greater than the pivot are placed to its right, meanwhile the array of strings is changed in the same way as the array of doubles.
	 * @param a the array of doubles that is being partitioned
	 * @param string the array of strings that mirrors the changes in the array of doubles
	 * @param left the position where the algorithm starts partitioning
	 * @param right the position where the algorithm stops partitioning
	 * @return the final position of the pivot
	 */
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
				swap(a, leftMark, rightMark);
				swapString(string, leftMark++, rightMark--);
			}
		}
		swap(a, leftMark, right);
		swapString(string, leftMark, right);

		return leftMark;

	}

	/*
	 * Uses the quicksort algorithm to sort the given arrays.
	 * @param a the array of doubles that is being sorted
	 * @param string the array of strings that mirrors the changes to the array of doubles
	 * @param left the lowest index of the sub-array the algorithm must work on
	 * @param right the highest index of the sub-array the algorithm must work on
	 */
	public void quickSort(double[] a, String[] string, int left, int right) {

		if (left < right) {
			int pivotIndex = partition(a, string, left, right);
			quickSort(a, string, left, pivotIndex);
			quickSort(a, string, pivotIndex + 1, right);
		}
	}

}
