package aw.GUI.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aw.GUI.Information;
import aw.file.Job;

//The class Information can be seen as a wrapper method of the Job class,
//So its tests are similar to the job classes. 

public class InformationTest {
	Information info = new Information();
	Job job1 = new Job("10001,ce,2,bi,6,aj,2,bc,3,ab,1,af,4,ai,1");
	Job job2 = new Job("10021,ab,3,ae,2,ah,1,cf,1,bi,1");
	
@Test
	public void testSetJob() {
		info.setJob(job1);
		assertEquals(job1, info.getJob());
		info.setJob(job2);
		assertEquals(job2, info.getJob());
	}
	
@Test
	public void testGetJobID() {
		info.setJob(job2);
		assertEquals(10021, info.getJobID());
		info.setJob(job1);
		assertEquals(10001, info.getJobID());
	}

@Test
	public void testGetJobItem() {
		info.setJob(job1);
		assertEquals("ce", info.getJobItem(0));
		assertEquals("aj", info.getJobItem(2));
		assertEquals("af", info.getJobItem(5));
	}

@Test
	
	public void testNumberItems() {
		info.setJob(job1);
		assertEquals(7, info.numberItems());
		info.setJob(job2);
		assertEquals(5, info.numberItems());
	}

@Test
	
	public void testGetJobReward() {
		info.setJob(job1);
		assert(job1.getJobReward() == info.getJobReward());
		info.setJob(job2);
		assert(job2.getJobReward() == info.getJobReward());
	}
}
