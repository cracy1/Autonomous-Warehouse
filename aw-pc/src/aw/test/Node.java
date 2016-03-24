package aw.test;

public class Node{
	public static final Node ZERO = new Node(0, 0);
	
    public int x;

	public int y;

    public Node(int x, int y){
        this.x = x;
        this. y = y;
    }

    @Override
    public boolean equals(Object o){
        Node n = (Node) o;
        return x == n.x && y == n.y;
    }

    @Override
    public String toString(){
        return "Node: " + this.x + ", " + this.y;
    }

	public int getY() {
		return y;
	}
	
	public int getX(){
		return x;
	}
}