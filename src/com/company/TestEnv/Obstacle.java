package com.company.TestEnv;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Obstacle {
    private Node position;

    public Obstacle(Node position){
        this.position = position;
    }

    public Node getPosition(){
        return position;
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

            cx = padding + position.x * xScale;
            cy = padding + position.y * yScale;
        }

        g2.setColor(Color.decode("0x444444"));
        g2.fill(new Rectangle2D.Float(cx - cellWidth/2, cy - cellHeight/2, cellWidth, cellHeight));
    }

    @Override
    public boolean equals(Object obj){
        Obstacle obs = (Obstacle) obj;
        return obs.position.equals(this.position);
    }
}
