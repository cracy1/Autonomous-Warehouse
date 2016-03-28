package com.company.TestEnv;

public class Item {
    private String id;
    private Node position;
    private float value;
    private float weight;
    private int amount;

    public Item(){
        this.amount = 1;
    }

    public Item(String id, Node position, float value, float weight){
        this.id = id;
        this.position = position;
        this.value = value;
        this.weight = weight;
        this.amount = 1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(Node position) {
        this.position = new Node(7 - position.y, 11 - position.x);
        //this.position = position;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Node getPosition() {
        return position;
    }

    public float getValue() {
        return value;
    }

    public float getWeight() {
        return weight;
    }

    public int getAmount(){
        return amount;
    }

    public Item clone(){
        return new Item(id, position, value, weight);
    }

    @Override
    public String toString(){
        return "Item: " + id + ", Location: " + position.x + "," + position.y + ", Value: " + value + ", Weight: " + weight + ", Amount: " + amount;
    }

    @Override
    public boolean equals(Object obj){
        Item item = (Item) obj;
        return id.equals(item.getId());
    }
}
