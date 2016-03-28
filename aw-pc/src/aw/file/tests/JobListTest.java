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
		assertEquals(100, list.numberJobs());
	}

	@org.junit.Test
	public void testJobID() {
		assertEquals(10000, job1.getID());
		assertEquals(10001, job2.getID());
		assertEquals(10002, job3.getID());
	}
	
	@org.junit.Test
	public void  testGetItem(){
		assertEquals("ab", job1.getItem(2));
		assertEquals("aa", job2.getItem(1));
		assertEquals("ah", job3.getItem(0));
	}
	
	@org.junit.Test
	public void testGetQuantity(){
		assertEquals(2, job1.getQuantity(2));
		assertEquals(4, job2.getQuantity(1));
		assertEquals(1, job3.getQuantity(0));
	}
	
	@org.junit.Test
	public void testNumberItems(){
		assertEquals(7, job1.numberItems());
		assertEquals(3, job2.numberItems());
		assertEquals(4, job3.numberItems());
	}
	
	@org.junit.Test
	public void testGetItemReward(){
		assertEquals(10.0, job1.getItemReward(2), 0.01);
		assertEquals(44.88, job2.getItemReward(1), 0.01);
		assertEquals(20.16, job3.getItemReward(0), 0.01);
	}
	
	@org.junit.Test
	public void testGetJobReward(){		
		assertEquals(131.9 , job1.getJobReward(), 0.01);
		assertEquals(146.64 ,job2.getJobReward(), 0.01);
		assertEquals(100.98 ,job3.getJobReward(), 0.01);
	}
	
	@org.junit.Test
	public void testGetUtility(){
		assertEquals(18.84, job1.getUtility(), 0.01);
		assertEquals(48.87, job2.getUtility(), 0.01);
		assertEquals(25.245, job3.getUtility(), 0.01);
	}

}
