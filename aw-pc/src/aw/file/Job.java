package aw.file;


import java.util.LinkedList;
import java.util.List;

import aw.test.Node;

public class Job implements Comparable<Job>{
    private int id;
    private Node dropPoint;
    private List<Item> items;

    public void setId(int id) {
        this.id = id;
        this.items = new LinkedList<>();
    }

    public void setDropPoint(Node dropPoint) {
        this.dropPoint = dropPoint;
    }

    public void addItem(Item item, int amount){
        Item clone = item.clone();
        clone.setAmount(amount);
        items.add(clone);
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public Node getDropPoint() {
        return dropPoint;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getValue(){
        double value = 0;
        int numOfItems = 0;

        for(Item item: items){
            numOfItems += item.getAmount();
            value += item.getValue() * item.getAmount();
        }

        value /= numOfItems;

        return value;
    }

    @Override
    public String toString(){
        return "Job: " + id + " Items: " + items + " Drop point: " + dropPoint + " Value: " + getValue();
    }

    @Override
    public int compareTo(Job job) {
        if(this.getValue() > job.getValue()) return -1;
        else return 1;
    }
}
