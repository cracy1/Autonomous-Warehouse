package aw.file.tests;

import static org.junit.Assert.*;

import aw.file.Job;
import aw.file.JobList;

public class JobListTest {
	
	JobList list = new JobList();
	Job job1 = new Job(list.getJob(0));
	Job job2 = new Job(list.getJob(1));
	Job job3 = new Job(list.getJob(2));

	@org.junit.Test
	public void testNumberJobs() {
		assertEquals(20000, list.numberJobs());
	}

	@org.junit.Test
	public void testJobID() {
		assertEquals(10000, job1.getID());
		assertEquals(10001, job2.getID());
		assertEquals(10002, job3.getID());
	}
	
	@org.junit.Test
	public void  testGetItem(){
		assertEquals("cb", job1.getItem(2));
		assertEquals("ab", job2.getItem(4));
		assertEquals("af", job3.getItem(0));
	}
	
	@org.junit.Test
	public void testGetQuantity(){
		assertEquals(3, job1.getQuantity(2));
		assertEquals(1, job2.getQuantity(4));
		assertEquals(4, job3.getQuantity(0));
	}
	
	@org.junit.Test
	public void testNumberItems(){
		assertEquals(3, job1.numberItems());
		assertEquals(7, job2.numberItems());
		assertEquals(5, job3.numberItems());
	}
	
	@org.junit.Test
	public void testGetItemReward(){
		assertEquals(19.16, job1.getItemReward(2), 0.01);
		assertEquals(41.76, job2.getItemReward(1), 0.01);
		assertEquals(18.83, job3.getItemReward(4), 0.01);
	}
	
	@org.junit.Test
	public void testGetJobReward(){		
		assertEquals(71.97 , job1.getJobReward(), 0.01);
		assertEquals(213.50 ,job2.getJobReward(), 0.01);
		assertEquals(177.24 ,job3.getJobReward(), 0.01);
	}
	
	@org.junit.Test
	public void testGetUtility(){
		assertEquals(23.99, job1.getUtility(), 0.01);
		assertEquals(30.50, job2.getUtility(), 0.01);
		assertEquals(35.448, job3.getUtility(), 0.01);
	}

}
