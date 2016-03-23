package aw.test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import aw.GUI.GUI;
import aw.file.Job;
import aw.file.JobList;

/**
 * Tester class for the grid.
 * @author jon woodburn
 *
 */
public class GUITest {

	public static void main(String[] args) throws InterruptedException {
		JobList jobList = new JobList();
		GUI guiTest = new GUI(jobList);
		Job job = new Job("10003,ah,1,bi,1");
		Job job2 = new Job("10019,af,3,ah,2,ae,4,bi,2,ab,2,aj,1");
		Job job3 = new Job("10039,cb,2,ae,2,aj,2,cf,3,bh,1,ah,3,ab,2,bi,1");
		guiTest.setJob(job, "Ricardo");
		guiTest.setJob(job2, "NXT");
		guiTest.setJob(job3, "Dave");
		Job job4 = new Job("10004,ae,4,ab,4,af,3,ah,1,aa,1,aj,2,ce,1");
		Job job5 = new Job("10020,ah,2,bi,3,ci,2");
		Job job6 = new Job("10040,cj,2,ah,2,af,1");
		
		Node node = new Node(0,0);
		Node node1 = new Node(0, 1);
		Node node2 = new Node(0, 2);
		Node node3 = new Node(0, 3);
		Node node4 = new Node(0, 4);
		Node node5 = new Node(0, 5);
		Node node6 = new Node(0, 6);
		Node node7 = new Node(1, 6);
		
		LinkedList<Node> path1 = new LinkedList<Node>();
		path1.add(node);
		path1.add(node1);
		path1.add(node2);
		path1.add(node3);
		path1.add(node4);
		path1.add(node5);
		path1.add(node6);
		path1.add(node7);
		
		guiTest.setRoute(path1, "Ricardo");
		
		TimeUnit.SECONDS.sleep(4);
		guiTest.setJob(job4, "Ricardo");
		TimeUnit.SECONDS.sleep(1);
		guiTest.setJob(job5, "NXT");
		TimeUnit.SECONDS.sleep(1);
		guiTest.setJob(job6, "Dave");
		
		
	}

}
