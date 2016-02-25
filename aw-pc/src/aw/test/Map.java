package aw.test;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by aranscope on 2/22/16.
 */

public class Map {
    int width;
    int height;

    public Map(int width, int height){
        this.width = width;
        this.height = height;
    }

    private int heuristic(Node a, Node b){
        return heuristic(a.x, a.y, b);
    }

    private int heuristic(int x, int y, Node b){
        return Math.abs(b.x - x) + Math.abs(b.y - y);
    }

    public Node min(LinkedList<Node> list, int[][] fscore, Node g) {
        Node min = list.get(0);
        for (Node n : list) if (fscore[n.x][n.y] < fscore[min.x][min.y]) min = n;
        return min;
    }

    public String getMoves(LinkedList<Node> nodes, int angle){
        String moves = "";

        Node currentNode = nodes.removeFirst();
        for(Node node: nodes){
            switch(angle){
                case 0:
                    if (node.y > currentNode.y){
                        moves += "tf";
                        angle = 180;
                    }
                    else if (node.x > currentNode.x){
                        moves += "rf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "f";
                        angle = 0;
                    }
                    else if (node.x < currentNode.x){
                        moves += "lf";
                        angle = 270;
                    }
                    break;
                case 90:
                    if (node.y > currentNode.y){
                        moves += "rf";
                        angle = 180;
                    }
                    else if (node.x > currentNode.x){
                        moves += "f";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "lf";
                        angle = 0;
                    }
                    else if (node.x < currentNode.x){
                        moves += "tf";
                        angle = 270;
                    }
                    break;
                case 180:
                    if (node.y > currentNode.y){
                        moves += "f";
                        angle = 180;
                    }
                    else if (node.x > currentNode.x){
                        moves += "lf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "tf";
                        angle = 0;
                    }
                    else if (node.x < currentNode.x){
                        moves += "rf";
                        angle = 270;
                    }
                    break;
                case 270:
                    if (node.y > currentNode.y){
                        moves += "lf";
                        angle = 180;
                    }
                    else if (node.x > currentNode.x){
                        moves += "tf";
                        angle = 90;
                    }
                    else if (node.y < currentNode.y){
                        moves += "rf";
                        angle = 0;
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

    private LinkedList<Node> recPath(HashMap<Node, Node> camefrom, Node current){
        LinkedList<Node> path = new LinkedList<>();
        path.add(current);

        while(camefrom.keySet().contains(current)){
            current = camefrom.get(current);
            path.add(0, current);
        }
        return path;
    }
    
    public LinkedList<Node> getPath(Node a, Node b){
    	boolean[][] walkable = new boolean[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                walkable[x][y] = true;

            }
        }


        for(int x = 1; x <= 6; x++){
            for(int y = 1; y <= 10; y += 3){
                walkable[x][y] = false;
            }
        }
        
        return getPath(a, b, walkable);
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
            if(current.equals(b)) return recPath(camefrom, current);

            openSet.remove(current);
            closedSet.add(current);


            for(int y = current.y - 1; y <= current.y + 1; y++){
                for (int x = current.x - 1; x <= current.x + 1; x++) {
                    if(x >= 0 && y >= 0 && x < width && y < height && (x == current.x || y == current.y)){
                        Node ne = new Node(x, y);

                        if(!ne.equals(current)) {
                            if (closedSet.contains(ne)) continue;

                            int tgscore = gscore[current.x][current.y] + 1;

                            if (!openSet.contains(ne)) openSet.add(ne);
                            else if (tgscore >= gscore[ne.x][ne.y]) continue;

                            camefrom.put(ne, current);
                            gscore[ne.x][ne.y] = tgscore;
                            fscore[ne.x][ne.y] = gscore[ne.x][ne.y] + heuristic(ne, b);
                        }
                    }
                }
            }
        }

        return null;
    }
}