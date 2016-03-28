package com.company.TestEnv;

import javax.swing.*;
import java.awt.*;

public class Controller extends JPanel implements Runnable{
    private GridMap gridMap;
    private Map map;
    private Robot rob1, rob2, rob3;

    public Controller(){
        setupMap();
        setupRobots();
        setupFrame();
        programLoop();
    }

    public void programLoop(){
        JobList list = new JobList();

        while(true) {

            Job job1 = list.getJob();

            Job job2 = list.getJob();

            Job job3 = list.getJob();

            for (Item i : job1.getItems()) {
                rob1.addWaypoints(map.getPath(rob1, i.getPosition()));
                //gridMap.addItem(i);
            }

            for(Item i: job2.getItems()){
                rob2.addWaypoints(map.getPath(rob2, i.getPosition()));

            }

            for(Item i: job3.getItems()){
                rob3.addWaypoints(map.getPath(rob3, i.getPosition()));

            }

            rob1.addWaypoints(map.getPath(rob1, job1.getDropPoint()));
            rob2.addWaypoints(map.getPath(rob2, job2.getDropPoint()));
            rob3.addWaypoints(map.getPath(rob3, job3.getDropPoint()));

            try{
                Thread.sleep(50);
            }catch(Exception ex){}
        }



//        for(int x = 0; x < 10; x++){
//            Node target = map.getRandomWalkableNode();
//            Node target2 = map.getRandomWalkableNode();
//            Node target3 = map.getRandomWalkableNode();
//
//            rob1.addWaypoints(map.getPath(rob1, target));
//            rob2.addWaypoints(map.getPath(rob2, target2));
//            rob3.addWaypoints(map.getPath(rob3, target3));
//        }
    }

    public void setupRobots(){
        rob1 = new Robot(new Vector(0, 0));
        rob2 = new Robot(new Vector(7, 0));
        rob3 = new Robot(new Vector(7, 11));

        gridMap.addRobot(rob1);
        gridMap.addRobot(rob2);
        gridMap.addRobot(rob3);
    }

    public void setupMap(){
        gridMap = new GridMap(8, 12, 445, 600, 50);
        gridMap.addTestObstacles();
        map = new Map(gridMap.getWidth(), gridMap.getHeight(), gridMap.getObstacles());

        this.setPreferredSize(new Dimension(445, 600));
    }

    public void setupFrame(){
        JFrame frame = new JFrame("Pathfinding");
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        new Thread(this).start();
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(20);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }

            gridMap.update();
            this.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //g2.rotate(45, getWidth()/2, getHeight()/2);
        g.clearRect(0, 0, getWidth(), getHeight());
        gridMap.draw(g2);
    }

    public static void main(String[] args){
        new Controller();
    }
}
