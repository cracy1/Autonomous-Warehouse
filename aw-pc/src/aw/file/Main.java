package aw.file;

public class Main {
	public static void main(String[] args) {
		Drop drop = new Drop();
		drop.sortClosestDropToRobot(3, 7);
		System.out.println(drop.getX(0) + " " + drop.getY(0));
		JobList jobList = new JobList();
		Job job1 = new Job(jobList.getJob(0));
	//	System.out.println(job1.getUtility());
		Job job2 = new Job(jobList.getJob(1));
	//	System.out.println(job2.getItemReward(1));
		Job job3 = new Job(jobList.getJob(2));
	//	System.out.println(job3.getUtility());
		// System.out.println(job.numberItems());
		// System.out.println(jobList.getJob(2));
		// System.out.println(job.getJobReward());

		// System.out.println(job.getItem(2) + " " + job.getQuantity(2) + " " +
		// job.getUtility());
		// System.out.println(jobList);
//		for (int i = 0; i < jobList.numberJobs(); i++)
//			System.out.println(job1.getUtility());
//		jobList.tell();
//		System.out.println(job2);
		//job2.sortItems(2, 4);
//		ItemList item = new ItemList();
//		for(int i = 0; i < job1.numberItems(); i++)
//			System.out.println(job1.getItem(i) + " " + item.getX(item.getIndex(job1.getItem(i)))+ " " + item.getY(item.getIndex(job1.getItem(i))));
//		System.out.println(job1);
//		Drop abc = new Drop();
//		System.out.println(abc);
//		for(int j = 0; j < abc.numberDrops(); j++){
//			System.out.println(abc.getX(j) + " " + abc.getY(j));
//		}
			
		
	}

}
