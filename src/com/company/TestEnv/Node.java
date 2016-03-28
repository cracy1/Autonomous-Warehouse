package com.company.TestEnv;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Node{
    public static final Node ZERO = new Node(0, 0);

    int x, y;

    public Node(int x, int y){
        this.x = x;
        this. y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public static Node translate(Node node){
        return new Node(7- node.y, 11 - node.x);
    }

    public Node(Vector vector){
        this((int)vector.x, (int)vector.y);
    }

    @Override
    public boolean equals(Object o){
        Node n = (Node) o;
        return x == n.x && y == n.y;
    }

    private float cx = -1, cy;
    private int cellWidth, cellHeight;
    public void draw(Graphics2D g2, int screenWidth, int screenHeight, int width, int height, int padding){
        if(cx == -1){
            int gridWidth = screenWidth - 2 * padding;
            int gridHeight = screenHeight - 2 * padding;

            int xScale = gridWidth / (width-1);
            int yScale = gridHeight / (height-1);

            cellWidth = gridWidth / (width-1);
            cellHeight = gridHeight / (height-1);

            cx = padding + x * xScale;
            cy = padding + y * yScale;
        }

        g2.setColor(Color.decode("0xbada55"));
        g2.fill(new Ellipse2D.Float(cx - cellWidth/2, cy - cellHeight/2, cellWidth, cellHeight));
    }

    @Override
    public String toString(){
        return "Node: " + this.x + ", " + this.y;
    }
}