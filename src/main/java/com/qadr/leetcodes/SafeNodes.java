package com.qadr.leetcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SafeNodes {
    public boolean isSafe(int[][] graph, int i, Map<Integer, Boolean> visited, Map<Integer, Boolean> saved){
        if(saved.containsKey(i)) return saved.get(i);
        if(visited.containsKey(i)) return false;
        if(graph[i].length == 0) return true;

        visited.put(i, true);

        for (int neighbour: graph[i]){
            if(!isSafe(graph, neighbour, visited, saved)) {
                saved.put(neighbour, false);
                return false;
            }
        }

        saved.put(i, true);
        return true;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> list = new ArrayList<>(graph.length);
        Map<Integer, Boolean> record = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if(isSafe(graph, i, new HashMap<>(), record)) list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new SafeNodes().eventualSafeNodes(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}}));
    }
}
