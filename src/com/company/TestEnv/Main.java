package com.company.TestEnv;
public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        JobList jobList = new JobList();

//        for(Job job: jobList.getJobs()){
//            System.out.println(job);
//        }

        System.out.print("Elapsed time (ms): " + (System.currentTimeMillis() - startTime));

        for(Job job: jobList.getJobs()){
            if(job.getItems().size() > 5) {
                printJob(job);

                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                }
            }
        }
    }

    public static void printJob(Job job){
        String[][] map = new String[12][12];

        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                map[i][j] = "  ";
            }
        }

        int count = 0;
        for(Item item: job.getItems()){
            map[item.getPosition().x][item.getPosition().y] = " " + count;
            count++;
        }

        map[job.getDropPoint().x][job.getDropPoint().y] = " D";

        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                System.out.print(map[j][i]);
            }

            System.out.println();
        }

        System.out.println("\n\n");
    }

}
