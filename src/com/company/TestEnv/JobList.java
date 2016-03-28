package com.company.TestEnv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

public class JobList {
    private String dropURL = "res/drops.csv";
    private String itemsURL = "res/items.csv";
    private String jobsURL = "res/jobs.csv";
    private String locationsURL = "res/locations.csv";

    private BufferedReader reader;

    private PriorityBlockingQueue<Job> jobs;
    private Map<String, Item> items;
    private List<Node> drops;

    public JobList(){
        jobs = new PriorityBlockingQueue<>();
        items = new HashMap<>();
        drops = new LinkedList<>();

        try {
            readItemsFile();
            readLocationsFile();
            readJobFile();
            readDropsFile();

            int i = 0;
            for(Job job: jobs){
                sortItems(job);
                i++;
                if(i > 1000) break;
            }

        }catch(IOException ex){
            System.out.println(ex.toString());
            System.err.println("Files could not be read.");
            System.exit(0);
        }
    }

    public Job getJob() {
        try {
            return jobs.take();
        }catch(InterruptedException ex){

        }

        return null;
    }

    public List<Node> getDrops(){
        return drops;
    }

    public Job getJob(Node location) throws InterruptedException{
        for(Job job: jobs){
            Node firstItem = job.getItems().get(0).getPosition();
            int manhattan = Math.abs(location.x - firstItem.x) + Math.abs(location.y - firstItem.y);
            if(manhattan < 4) {
                jobs.remove(job);
                return job;
            }
        }
        return jobs.take();
    }

    public PriorityBlockingQueue<Job> getJobs(){
        return jobs;
    }


    private void readItemsFile() throws IOException{
        reader = new BufferedReader(new FileReader(itemsURL));
        String line;

        while((line = reader.readLine()) != null) {
            String[] lineSplit = line.split(",");
            Item item = new Item();
            item.setId(lineSplit[0]);
            item.setValue(Float.parseFloat(lineSplit[1]));
            item.setWeight(Float.parseFloat(lineSplit[2]));
            items.put(item.getId(), item);
        }

        reader.close();
    }

    private void readLocationsFile() throws IOException{
        reader = new BufferedReader(new FileReader(locationsURL));
        String line;

        while((line = reader.readLine()) != null) {
            String[] lineSplit = line.split(",");
            int x = Integer.parseInt(lineSplit[0]);
            int y = Integer.parseInt(lineSplit[1]);
            items.get(lineSplit[2]).setPosition(new Node(x, y));
        }

        reader.close();
    }

    private void readJobFile() throws IOException{
        reader = new BufferedReader(new FileReader(jobsURL));
        String line;

        while((line = reader.readLine()) != null){
            String[] lineSplit = line.split(",");
            Job job = new Job();
            job.setId(Integer.parseInt(lineSplit[0]));
            for(int i = 1; i < lineSplit.length - 1; i+=2){
                job.addItem(items.get(lineSplit[i]), Integer.parseInt(lineSplit[i+1]));
            }

            jobs.add(job);
        }

        reader.close();
    }

    private void readDropsFile() throws IOException{
        reader = new BufferedReader(new FileReader(dropURL));
        String line;

        while((line = reader.readLine()) != null){
            String[] lineSplit = line.split(",");
            int x = Integer.parseInt(lineSplit[0]);
            int y = Integer.parseInt(lineSplit[1]);
            drops.add(new Node(x, y));
        }

        Random ra = new Random();
        for(Job job: jobs){
            job.setDropPoint(drops.get(ra.nextInt(drops.size())));
        }

        reader.close();
    }

    public List<List<Item>> generatePerm(List<Item> original) {
        if (original.size() == 0) {
            List<List<Item>> result = new ArrayList<List<Item>>();
            result.add(new ArrayList<Item>());
            return result;
        }
        Item firstElement = original.remove(0);
        List<List<Item>> returnValue = new ArrayList<List<Item>>();
        List<List<Item>> permutations = generatePerm(original);
        for (List<Item> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<Item> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    private void sortItems(Job job){
        List<List<Item>> permutations = generatePerm(job.getItems());
        job.setItems(permutations.get(0));
        int minPathLength = getPathLength(job.getItems(), job.getDropPoint());

        for(List<Item> permutation: permutations){
            for(Node dropPoint: drops){
                int newPathLength = getPathLength(permutation, dropPoint);
                if(newPathLength < minPathLength){
                    minPathLength = newPathLength;
                    job.setItems(permutation);
                    job.setDropPoint(dropPoint);
                }
            }
        }
    }


    private int getPathLength(List<Item> items, Node dropPoint){
        int length = 0;

        for(int i = 0; i < items.size() - 1; i++){

            Node pos1 = items.get(i).getPosition();
            Node pos2 = items.get(i + 1).getPosition();

            if(i == 0){
                length += Math.abs(dropPoint.x - pos1.x) + Math.abs(dropPoint.y - pos1.y);
            }

            length += Math.abs(pos2.x - pos1.x) + Math.abs(pos2.y - pos1.y);

            if(i == items.size() - 1){
                length += Math.abs(dropPoint.x - pos2.x) + Math.abs(dropPoint.y - pos2.y);
            }
        }

        //Node lastItem = items.get(items.size() - 1).getPosition();
        //length += Math.abs(lastItem.x - dropPoint.x) - Math.abs(lastItem.y - dropPoint.y);

        return length;
    }
}
