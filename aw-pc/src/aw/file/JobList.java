package aw.file;

import java.io.IOException;

import aw.file.interfaces.JobListInterface;
import aw.robotics.Robot;

public class JobList implements JobListInterface {

	String path = "res/";
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
		sort();

	}

	public int numberJobs(){
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

	private  void quickSort(double [] a, String[] b, int left, int right)
    {
        if (left < right)
        {
            int pivotIndex = (left+right)/2;
            int pos = partition(a, b, left,right,pivotIndex);
            quickSort(a,b,left,pos-1);
            quickSort(a,b,pos+1,right);
        }
    }
	
	private  void swap(double[] a, int i, int j) {
	    double t = a[i];
	    a[i] = a[j];
	    a[j] = t;
	}
	
	private  void swap2(String[] a, int i, int j){
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private  int partition(double [] a, String[] b, int left,int right,int pivotIndex)
	{
	    swap(a, pivotIndex, right);
	    swap2(b, pivotIndex, right);
	    int pos = left;//represents boundary between small and large elements
	    for(int i = left; i < right; i++)
	    {
	        if (a[i] < a[right])
	        {
	            swap(a, i, pos);
	            swap2(b, i, pos);
	            pos++;
	        }
	    }
	    swap(a, right, pos);
	    swap2(b, right, pos);
	    return pos;
	}

	
	@Override
	public void sort() {
		quickSort(utility, job, 0, utility.length);

	}
}


