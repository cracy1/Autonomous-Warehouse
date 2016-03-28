package com.company.TestEnv;

import com.sun.org.apache.xpath.internal.operations.Mult;

import java.util.LinkedList;

/**
 * Created by aranscope on 3/22/16.
 */
public class MultiRobotMapTester {
    public static void main(String[] args){

//        MultiRobotMap map = new MultiRobotMap(8, 12);
//
//        Object rob1 = new Object();
//        Object rob2 = new Object();
//        Object rob3 = new Object();
//
//        LinkedList<Node> path = map.getPath(new Node(0, 0), new Node(4, 5), rob1);
//        LinkedList<Node> path2 = map.getPath(new Node(5, 6), new Node(1, 0), rob2);
//        LinkedList<Node> path3 = map.getPath(new Node(5, 7), new Node(7, 6), rob3);
//        printMap(path);
//        printMap(path2);
//        printMap(path3);
    }

    public static void printMap(LinkedList<Node> path){
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 12; y++){
                System.out.print(path.contains(new Node(x, y)) ? " *" : "  ");
            }
            System.out.println();
        }

    }
}
