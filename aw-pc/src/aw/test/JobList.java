package aw.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import aw.file.Job;
import aw.file.interfaces.JobListInterface;
import aw.robotics.Robot;

public class JobList {
	private String filepath = "res/jobs.csv";
	private LinkedList<Job> jobs;
	
	public JobList(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			System.err.println("Failed to read jobs.csv");
			System.exit(0);
		}		
		
		String line;
		
		try {
			while((line = reader.readLine()) != null){
				String[] lineSplit = line.split(",");
				int id = Integer.parseInt(lineSplit[0]);
				
				for(int i = 1; i < lineSplit.length; i+=2){
					String itemName = lineSplit[i];
					int itemQuantity = Integer.parseInt(lineSplit[i+1]);
					//Item item = new Item(itemName, itemQuantity, itemQuantity, itemQuantity, itemQuantity)
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sort() {
		// TODO Auto-generated method stub
		
	}

}
