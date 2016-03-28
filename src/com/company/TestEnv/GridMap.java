package com.company.TestEnv;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by aranscope on 3/4/16.
 */
public class GridMap {
    private int width, height;
    private int screenWidth, screenHeight;
    private int padding;

    private LinkedList<Robot> robots;
    private LinkedList<Obstacle> obstacles;
    private LinkedList<Item> items;
    private List<Node> drops;

    public GridMap(int width, int height, int screenWidth, int screenHeight, int padding){
        this.width = width;
        this.height = height;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.padding = padding;

        this.robots = new LinkedList<>();
        this.obstacles = new LinkedList<>();
        this.items = new LinkedList<>();
        drops = new JobList().getDrops();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public LinkedList<Obstacle> getObstacles(){
        return obstacles;
    }

    public void addTestObstacles(){


        for(int x = 2; x < 6; x++){
            addObstacle(new Obstacle(new Node(x, 2)));
            addObstacle(new Obstacle(new Node(x, 9)));
        }

        addObstacle(new Obstacle(new Node(3, 5)));
        addObstacle(new Obstacle(new Node(3, 6)));
        addObstacle(new Obstacle(new Node(4, 5)));
        addObstacle(new Obstacle(new Node(4, 6)));
    }

    public void addRandomObstacles(int num){
        Random ra = new Random();
        for(int i = 0; i < num; i++){
            addObstacle(new Obstacle(new Node(ra.nextInt(width), ra.nextInt(height))));
        }
    }

    public void addRobot(Robot robot){
        robots.add(robot);
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void update(){
        for(Robot robot: robots){
            robot.update();
        }
    }

    public void draw(Graphics2D g2){
        int gridWidth = screenWidth - 2 * padding;
        int gridHeight = screenHeight - 2 * padding;

        int xScale = gridWidth / (width-1);
        int yScale = gridHeight / (height-1);

        g2.setColor(Color.decode("0xdddddd"));

        for(int x = 0; x < width; x++){
            g2.drawLine(padding + x * xScale, padding, padding + x * xScale, screenHeight - padding);
        }

        for(int y = 0; y < height; y++){
            g2.drawLine(padding, padding + y * yScale, screenWidth - padding, padding + y * yScale);
        }

        for(Obstacle obstacle: obstacles){
            obstacle.draw(g2, screenWidth, screenHeight, width, height, padding);
        }

        for(Node drop: drops){
            //drop.draw(g2, screenWidth, screenHeight, width, height, padding);
        }

        for(Robot robot: robots) {
            robot.draw(g2, screenWidth, screenHeight, width, height, padding);
        }

//        for(Item item: items){
//            item.draw(g2, screenWidth, screenHeight, width, height, padding);
//        }
    }
}
