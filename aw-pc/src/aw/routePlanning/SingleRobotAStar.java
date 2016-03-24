package aw.routePlanning;

import java.util.HashMap;
import java.util.LinkedList;

import aw.test.MapParser;
import aw.test.Node;

import java.util.ArrayList;

public class SingleRobotAStar {
	private int[][] heuristicMap;
	private int[][] movementCostMap;
	private int[][] totalCostMap;

	private final int width = 12;
	private final int height = 8;

	private ArrayList<Node> openSet;
	private ArrayList<Node> closeSet;

	private HashMap<Node, Node> previousNodeMap;
	
	
	public SingleRobotAStar() {
		
		heuristicMap = new int[width][height];
		movementCostMap = new int[width][height]; // g score
		previousNodeMap = new HashMap<Node, Node>();
		totalCostMap = new int[width][height]; // f score
		

		openSet = new ArrayList<Node>();
		closeSet = new ArrayList<Node>();
	

		
	}

	public LinkedList<Node> findRoute(Node start, Node goal) {
		heuristicMap = new int[width][height];
		movementCostMap = new int[width][height]; // g score
		previousNodeMap = new HashMap<Node, Node>();
		totalCostMap = new int[width][height]; // f score
		

		openSet = new ArrayList<Node>();
		closeSet = new ArrayList<Node>();
		
		for(Node node: new MapParser().getObstacles()){
			closeSet.add(node);
		}
		
		// setting all default values
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				heuristicMap[x][y] = (int) (Math.abs(goal.getX() -x)
						+ Math.abs(goal.getY() - y));

				movementCostMap[x][y] = Integer.MAX_VALUE;

				totalCostMap[x][y] = Integer.MAX_VALUE;

			}
		}
		

		
	
	
		// adding start node to the openSet
		openSet.add(start);
		Node currentNode;
	
		movementCostMap[start.getX()][start.getY()] = 0;
		while (!openSet.isEmpty()) {
		
			currentNode = getSmallestValue();
			
		
			openSet.remove(currentNode);
			closeSet.add(currentNode);
		
			
			if (currentNode.equals(goal)) {
			
				// return completed path
				return constructPath(currentNode);
			}

			for (int x = currentNode.getX() - 1; x <= currentNode.getX() + 1; x++) {
				for (int y = currentNode.getY() - 1; y <= currentNode.getY() + 1; y++) {
					if (x < width && x >= 0 && y >= 0 && y < height && (x == currentNode.getX() || y == currentNode.getY())) {
							
					
						
						Node neighbour = new Node(x, y);
					
						if (closeSet.contains(neighbour)) {
							// go to next loop
							continue;
						}
			
						int tentativeGScore = movementCostMap[currentNode.getX()][currentNode.getY()] + 1;
						if (!openSet.contains(neighbour)) {
							openSet.add(neighbour);
						} else if (tentativeGScore >= movementCostMap[neighbour.getX()][neighbour.getY()]) {
							// go to next loop
							continue;
						}
						previousNodeMap.put(neighbour, currentNode);
						movementCostMap[neighbour.getX()][neighbour.getY()] = tentativeGScore;
						totalCostMap[neighbour.getX()][neighbour.getY()] = tentativeGScore
								+ heuristicMap[neighbour.getX()][neighbour.getY()];
						
					}
				}
			}

		}
		// no path found
		new Exception("no path found");
		return null;

	}

	private LinkedList<Node> constructPath(Node currentNode) {
		LinkedList<Node> path = new LinkedList<>();
		path.add(currentNode);
		while (previousNodeMap.keySet().contains(currentNode)) {
			currentNode = previousNodeMap.get(currentNode);
			path.add(0, currentNode);
		}

		return path;
	}
	
	/**
     * Get a string of moves from a list of nodes and a starting angle.
     * @param nodes List of nodes in the route.
     * @param angle Angle of the agent.
     * @return String of moves.
     */
    public static String getMoves(LinkedList<Node> nodes, int angle){
        String moves = "";
        if(nodes == null) return "";
        Node currentNode = nodes.get(0);
        for(Node node: nodes){
            switch(angle){
                case 0:
                    if (node.y > currentNode.y){
                        moves += "f";
                    }
                    else if (node.x > currentNode.x){
                        moves += "rf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "tf";
                        angle = 180;
                    }
                    else if (node.x < currentNode.x){
                        moves += "lf";
                        angle = 270;
                    }
                    break;
                case 90:
                    if (node.y > currentNode.y){
                        moves += "lf";
                        angle = 0;
                    }
                    else if (node.x > currentNode.x){
                        moves += "f";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "rf";
                        angle = 180;
                    }
                    else if (node.x < currentNode.x){
                        moves += "tf";
                        angle = 270;
                    }
                    break;
                case 180:
                    if (node.y > currentNode.y){
                        moves += "tf";
                        angle = 0;
                    }
                    else if (node.x > currentNode.x){
                        moves += "lf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "f";
                        angle = 180;
                    }
                    else if (node.x < currentNode.x){
                        moves += "rf";
                        angle = 270;
                    }
                    break;
                case 270:
                    if (node.y > currentNode.y){
                        moves += "rf";
                        angle = 0;
                    }
                    else if (node.x > currentNode.x){
                        moves += "tf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "lf";
                        angle = 180;
                    }
                    else if (node.x < currentNode.x){
                        moves += "f";
                        angle = 270;
                    }
                    break;
            }

            currentNode = node;
        }

        return moves;
    }


	private Node getSmallestValue() {
		Node min;
		min = openSet.get(0);
		for (Node n : openSet) {
			if (totalCostMap[n.getX()][ n.getY()] < totalCostMap[min.getX()][min.getY()]) {
				
				min = n;
			}
		}
		return min;
	}
}