package com.company.TestEnv;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by aranscope, Sam Dowell and George Alexander on 2/22/16.
 */

public class Map {
    int width;
    int height;
    private LinkedList<Obstacle> obstacles;

    /**
     * Construct a new map.
     * @param width Width of the map.
     * @param height Height of the map.
     */
    public Map(int width, int height, LinkedList<Obstacle> obstacles){
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    /**
     * Manhattan heuristic between two nodes.
     * @param a The first node.
     * @param b The second node.
     * @return The distance between the two nodes.
     */
    private int heuristic(Node a, Node b){
        return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
    }

    public Node min(LinkedList<Node> list, int[][] fscore, Node g) {
        Node min = list.get(0);
        for (Node n : list) if (fscore[n.x][n.y] < fscore[min.x][min.y]) min = n;
        return min;
    }

    /**
     * Generate a path from a current node and a map of parent nodes.
     * @param cameFrom
     * @param current
     * @return A list of nodes, representing a route.
     */
    private LinkedList<Node> recPath(HashMap<Node, Node> cameFrom, Node current){
        LinkedList<Node> path = new LinkedList<>();
        path.add(current);

        while(cameFrom.keySet().contains(current)){
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }

    public Node getRandomWalkableNode(){
        Random ra = new Random();

        for(int x = 0; x < 10; x++){
            int rx = ra.nextInt(width);
            int ry = ra.nextInt(height);
            if(!obstacles.contains(new Obstacle(new Node(rx, ry)))){
                return new Node(rx, ry);
            }
        }


        return new Node(0, 0);
    }

    /**
     * Get a list of nodes representing a route between two nodes.
     * @param rob
     * @param target
     * @return
     */
    public LinkedList<Node> getPath(Robot rob, Node target){
        boolean[][] walkable = new boolean[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                walkable[x][y] = true;

            }
        }

        for(Obstacle obs: obstacles){
            walkable[obs.getPosition().x][obs.getPosition().y] = false;
        }

        return getPath(rob.getLastWaypoint(), target, walkable);
    }

    public LinkedList<Node> getPath(Node a, Node b, boolean[][] walkable){
        LinkedList<Node> closedSet = new LinkedList<>();
        LinkedList<Node> openSet = new LinkedList<>();
        openSet.add(a);

        int[][] fscore = new int[width][height];
        int[][] gscore = new int[width][height];

        HashMap<Node, Node> camefrom = new HashMap<>();

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                fscore[x][y] = Integer.MAX_VALUE;
                gscore[x][y] = Integer.MAX_VALUE;
                if(!walkable[x][y]) closedSet.add(new Node(x, y));
            }
        }

        gscore[a.x][a.y] = 0;
        fscore[a.x][a.y] = heuristic(a, b);

        while(!openSet.isEmpty()){
            Node current = min(openSet, fscore, b);
            if(current.equals(b)) return recPath(camefrom, current);	// if we are at the goal node
            // return the path
            openSet.remove(current);
            closedSet.add(current);


            for(int y = current.y - 1; y <= current.y + 1; y++){		// these two for loops go through the
                for (int x = current.x - 1; x <= current.x + 1; x++) {	// current node's neighbours
                    if(x >= 0 && y >= 0 && x < width && y < height && (x == current.x || y == current.y)){
                        Node ne = new Node(x, y);

                        if(!ne.equals(current)) {
                            if (closedSet.contains(ne)) continue; // exits that iteration of the for loop

                            int tgscore = gscore[current.x][current.y] + 1; // the move cost to get to the node from
                            // the current path.
                            if (!openSet.contains(ne)) openSet.add(ne);		// adds the neighbour to the open set if its not present
                            else if (tgscore >= gscore[ne.x][ne.y]) continue; // if there is a quicker way to the current
                            //neighbour then ignore the path
                            camefrom.put(ne, current);
                            gscore[ne.x][ne.y] = tgscore;
                            fscore[ne.x][ne.y] = gscore[ne.x][ne.y] + heuristic(ne, b);
                        }
                    }
                }
            }
        }

        //we have failed to find a path

        return new LinkedList<>();
    }
}