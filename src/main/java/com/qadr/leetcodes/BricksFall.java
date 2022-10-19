package com.qadr.leetcodes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BricksFall {


    private static void printArray(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    private static void printArray(int[][] arr) {
        for(int[] i : arr) {
            printArray(i);
            System.out.println();
        }
    }

    public static int findParent(int[] parent, Integer i){
        if(parent[i] != i){
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }

    private static void detectCycle(Integer[][] edges){
        Map<Integer, List<Integer>> graph = Graph.buildGraphFromEdges(edges);
        int[] parents = new int[edges.length * edges[0].length];
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                int index = i * edges[0].length + j;
                parents[index] = index;
            }
        }

        for (Integer[] edge : edges) {
            int node1 = edge[0], parent1 = findParent(parents, node1 - 1);
            int node2 = edge[1], parent2 = findParent(parents, node2 - 1);
            if (parent1 == parent2) {
                System.out.println("There is cycle");
                continue;
            }
            parents[parent1] = parents[parent1] + parents[parent2];
            parents[parent2] = node1;
        }

        printArray(parents);
    }

    public static void main(String[] args) {
//        int[][] grid = {{1,0,0,0},{1,1,0,0}}, hits = {{1,1},{1,0}};
        int[][] grid = {{1,0,1},{1,1,1}}, hits = {{0,0},{0,2},{1,1}};
//        int[][] grid = {{1,0,0,0},{1,1,1,0}}, hits = {{1,0}};
//        int[][] grid = {{1},{1},{1},{1},{1}},hits = {{3,0},{4,0},{1,0},{2,0},{0,0}};

//        Integer[][] edges = {{1,2},{3,4}, {5,6}, {7,8}, {2,4}, {2,5}, {1,3}, {6,8}, {5,7}};
//        detectCycle(edges);

    }
}
