package com.company.TestEnv;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Robot {
    private Vector position;
    private float vx = 0;
    private float vy = 0;
    private final float maxVel = 0.05f;

   // public LinkedBlockingQueue<Node> reservations;

    private LinkedList<Node> route;

    /**
     * Create a new robot.
     * @param position
     */
    public Robot(Vector position){
        this.position = position;
        this.route = new LinkedList<>();
        route.add(new Node(position));
    }

    /**
     * Get the position of the robot as a vector.
     * @return Position of the robot.
     */
    public Vector getPosition(){
        return position;
    }

    private float cx = -1, cy;
    private int cellWidth, cellHeight;
    private int xScale, yScale;

    /**
     * Draw the robot to the screen.
     * @param g2 The graphics 2d object.
     * @param screenWidth The width of the window.
     * @param screenHeight The height of the window.
     * @param width The width of the grid.
     * @param height The height of the grid.
     * @param padding The padding around the grid.
     */
    public void draw(Graphics2D g2, int screenWidth, int screenHeight, int width, int height, int padding){
        if(cx == -1){
            int gridWidth = screenWidth - 2 * padding;
            int gridHeight = screenHeight - 2 * padding;

            xScale = gridWidth / (width-1);
            yScale = gridHeight / (height-1);

            cellWidth = gridWidth / (width-1);
            cellHeight = gridHeight / (height-1);

            cellWidth /= 1.2;
            cellHeight /= 1.2;
        }

        cx = padding + position.x * xScale;
        cy = padding + position.y * yScale;

        drawPath(g2, xScale, yScale, padding);
        drawRobot(g2, cx, cy, cellWidth, cellHeight);
    }

    public void setLocation(Node node){
        this.position = new Vector(node);
    }

    private void drawRobot(Graphics2D g2, float cx, float cy, int cellWidth, int cellHeight){
        //drawing robot

        g2.setColor(Color.decode("0xE74C3C"));
        g2.fill(new Ellipse2D.Float(cx - cellWidth/2, cy - cellHeight/2, cellWidth, cellHeight));
    }

    private void drawPath(Graphics2D g2, float xScale, float yScale, int padding){

        Stroke orig = g2.getStroke();
        g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.GREEN);
        //drawing path
        Vector n1 = position;
        for(int i = 0; i < route.size(); i++){
            int alpha = 255 - ((i*25 > 255) ? 255: i * 25);
            g2.setColor(new Color(44, 62, 80, alpha));
            if(i > 0) n1 = new Vector(route.get(i-1));
            if(i > 10) break;
            Vector n2 = new Vector(route.get(i));
            Line2D.Float line = new Line2D.Float(padding + n1.x * xScale, padding + n1.y * yScale, padding + n2.x * xScale, padding + n2.y * yScale);
            g2.draw(line);
        }

        g2.setStroke(orig);
    }

    /**
     * Add a waypoint to the robots route.
     * @param node The waypoint to add to the route.
     */
    public void addWaypoint(Node node){
        if(node != null) route.add(node);
    }

    /**
     * Add a waypoint the the robot's route.
     * @param x The x point of the waypoint to add to the route.
     * @param y The y point of the waypoint to add to the route.
     */
    public void addWaypoint(int x, int y){
        addWaypoint(new Node(x, y));
    }

    public Node getLastWaypoint(){
        if(route.size() > 0) return route.getLast();
        else return new Node(position);
    }

    /**
     * Add a set of waypoints to the robot's route.
     * @param nodes The set of nodes to add.
     */
    public void addWaypoints(LinkedList<Node> nodes){
        for(Node node: nodes){
            route.add(node);
        }
    }

    /**
     * Update the robot's position.
     */
    public void update(){
        if(route.size() > 0){
            Node target = route.get(0);
            float dx = target.x - position.x;
            float dy = target.y - position.y;

            if(new Vector(target).equals(position)){ //target reached
                position = new Vector(target);
                route.remove(0);
            }
            else{
                vx = Math.signum(dx) * maxVel;
                vy = Math.signum(dy) * maxVel;

                this.position.x += vx;
                this.position.y += vy;
            }
        }
        else{
            vx = 0;
            vy = 0;
        }
    }
}
