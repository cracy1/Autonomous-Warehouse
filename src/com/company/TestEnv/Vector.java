package com.company.TestEnv;

public class Vector {
    public static final Vector ZERO = new Vector(0, 0);

    float x, y;

    public Vector(float x, float y){
        this.x = x;
        this. y = y;
    }

    public Vector(Node node){
        this(node.x, node.y);
    }

    @Override
    public boolean equals(Object o){
        Vector n = (Vector) o;

        return round(2).x == n.round(2).x && round(2).y == n.round(2).y;
    }

    public Vector round(int dp){
        return new Vector(round(x, dp), round(y, dp));
    }

    private float round(float value, int dp){
        float multVal = (float) Math.pow(10, dp);
        return Math.round(value * multVal) / multVal;
    }

    @Override
    public String toString(){
        return "com.Company.Vector: " + this.x + ", " + this.y;
    }
}