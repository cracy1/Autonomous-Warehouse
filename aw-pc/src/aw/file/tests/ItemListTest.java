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
		assertEquals("ab", item.getName(item.getIndex(job1.getItem(2))));
		assertEquals("cc", item.getName(item.getIndex(job2.getItem(0))));
		assertEquals("aa", item.getName(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetReward() {
		assertEquals(5.0, item.getReward(item.getIndex(job1.getItem(2))), 0.00);
		assertEquals(14.72, item.getReward(item.getIndex(job2.getItem(0))), 0.00);
		assertEquals(11.22, item.getReward(item.getIndex(job3.getItem(3))), 0.00);
	}

	@Test
	public void testGetWeight() {
		assertEquals(4.58, item.getWeight(item.getIndex(job1.getItem(2))), 0.00);
		assertEquals(5.7, item.getWeight(item.getIndex(job2.getItem(0))), 0.00);
		assertEquals(5.97, item.getWeight(item.getIndex(job3.getItem(3))), 0.00);
	}
	
	@Test
	public void testGetX() {
		assertEquals(7, item.getX(item.getIndex(job1.getItem(2))));
		assertEquals(3, item.getX(item.getIndex(job2.getItem(0))));
		assertEquals(3, item.getX(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetY() {
		assertEquals(8, item.getY(item.getIndex(job1.getItem(2))));
		assertEquals(10, item.getY(item.getIndex(job2.getItem(0))));
		assertEquals(7, item.getY(item.getIndex(job3.getItem(3))));
	}

	@Test
	public void testGetIndex() {
		assertEquals(1, item.getIndex(job1.getItem(2)));
		assertEquals(22, item.getIndex(job2.getItem(0)));
		assertEquals(0, item.getIndex(job3.getItem(3)));
	}

}
