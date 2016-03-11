package aw.file.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aw.file.ItemList;
import aw.file.Job;
import aw.file.JobList;

public class ItemListTest {
	
	JobList list = new JobList();
	Job job1 = new Job(list.getJob(0));
	Job job2 = new Job(list.getJob(1));
	Job job3 = new Job(list.getJob(2));
	ItemList item = new ItemList();

	@Test
	public void testGetName() {
		assertEquals("cb", item.getName(item.getIndex(job1.getItem(2))));
		assertEquals("ce", item.getName(item.getIndex(job2.getItem(0))));
		assertEquals("bf", item.getName(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetReward() {
		assertEquals(6.39, item.getReward(item.getIndex(job1.getItem(2))), 0.00);
		assertEquals(2.31, item.getReward(item.getIndex(job2.getItem(0))), 0.00);
		assertEquals(10.81, item.getReward(item.getIndex(job3.getItem(3))), 0.00);
	}

	@Test
	public void testGetWeight() {
		assertEquals(3.76, item.getWeight(item.getIndex(job1.getItem(2))), 0.00);
		assertEquals(5.36, item.getWeight(item.getIndex(job2.getItem(0))), 0.00);
		assertEquals(5.18, item.getWeight(item.getIndex(job3.getItem(3))), 0.00);
	}
	
	@Test
	public void testGetX() {
		assertEquals(7 - 5, item.getX(item.getIndex(job1.getItem(2))));
		assertEquals(7 - 1, item.getX(item.getIndex(job2.getItem(0))));
		assertEquals(7 - 4, item.getX(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetY() {
		assertEquals(11 - 8, item.getY(item.getIndex(job1.getItem(2))));
		assertEquals(11 - 5, item.getY(item.getIndex(job2.getItem(0))));
		assertEquals(11 - 3, item.getY(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetIndex() {
		assertEquals(21, item.getIndex(job1.getItem(2)));
		assertEquals(24, item.getIndex(job2.getItem(0)));
		assertEquals(15, item.getIndex(job3.getItem(3)));
	}

}
