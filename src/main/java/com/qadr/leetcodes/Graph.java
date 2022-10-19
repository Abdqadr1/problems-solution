package com.qadr.leetcodes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {
    public static Map<String, List<String>> getGraph(){
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("a", List.of("b", "c"));
        graph.put("b", List.of("d"));
        graph.put("c", List.of("e"));
        graph.put("e", Collections.emptyList());
        graph.put("d", Collections.emptyList());
        return graph;
    }
    public static Map<Integer, List<Integer>> getUndirectedGraph(){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(3, Collections.emptyList());
        graph.put(4, List.of(6));
        graph.put(6, List.of(4, 5, 7, 8));
        graph.put(8, List.of(6));
        graph.put(7, List.of(6));
        graph.put(5, List.of(6));
        graph.put(1, List.of(2));
        graph.put(2, List.of(1));
        return graph;
    }
    public static<T> Map<T, List<T>> buildGraphFromEdges(T[][] edges){
        Map<T, List<T>> graph = new HashMap<>();
        for (T[] edge : edges){
            T node1 = edge[0], node2 = edge[1];
            if(!graph.containsKey(node1))graph.put(node1, new ArrayList<>());
            if(!graph.containsKey(node2))graph.put(node2, new ArrayList<>());
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        return graph;
    }
    public static Map<Integer, List<Integer>> buildGraphLargest(){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(8,1,5));
        graph.put(1, List.of(0));
        graph.put(5, List.of(0,8));
        graph.put(8, List.of(0,5));
        graph.put(2, List.of(3,4));
        graph.put(3, List.of(2,4));
        graph.put(4, List.of(3,2));
        return graph;
    }

    static class DFS {
        public static void traverseGraph(Map<String, List<String>> graph, String source){
            Stack<String> stack = new Stack<>();
            stack.push(source);

            while (!stack.isEmpty()){
                String current = stack.pop();
                System.out.println(current);
                for (String neighbour : graph.get(current)) stack.push(neighbour);
            }
        }
        public static void traverseGraphRecursion(Map<String, List<String>> graph, String source){
            System.out.println(source);
            for (String neighbour : graph.get(source)){
                traverseGraphRecursion(graph, neighbour);
            }
        }

        public static boolean canReach(Map<String, List<String>> graph, String source, String destination){
            if(source.equals(destination)) return true;

            for (String neighbour : graph.get(source)){
                if(canReach(graph, neighbour, destination)) return true;
            }

            return false;
        }

        public static boolean undirectedPath(String[][] edges, String node1, String node2){
            Map<String, List<String>> graph = buildGraphFromEdges(edges);
            Map<String, Boolean> visited = new HashMap<>();
            return hasPathUndirected(graph, node1, node2, visited);
        }
        private static boolean hasPathUndirected(Map<String, List<String>> graph, String source, String dest, Map<String, Boolean> visited){
            if(source.equals(dest)) return true;
            if(visited.containsKey(source)) return false;

            visited.put(source, true);


            for (String neighbour : graph.get(source)){
                if(hasPathUndirected(graph, neighbour, dest, visited)) return true;
            }
            return false;
        }

        public static void traverseGraphRecursion(Map<Integer, List<Integer>> graph, Integer source, Map<Integer, Boolean> visited){
            if(visited.containsKey(source)) return;

            visited.put(source, true);
            for (Integer neighbour : graph.get(source)){
                traverseGraphRecursion(graph, neighbour, visited);
            }
        }
        public static int connectedComponentsCount(Map<Integer, List<Integer>> graph){
            Map<Integer, Boolean> visited = new HashMap<>();
            int count = 0;
            for (Integer key : graph.keySet()){
                if(!visited.containsKey(key)) {
                    count++;
                    traverseGraphRecursion(graph, key, visited);
                }
            }

            return count;
        }

        public static<T> int exploreSize(Map<T, List<T>> graph, T source, Map<T, Boolean> visited){
            if(visited.containsKey(source)) return 0;

            visited.put(source, true);
            int size = 1;
            for (T neighbour : graph.get(source)){
                size += exploreSize(graph, neighbour, visited);
            }
            return size;
        }
        public static int largestComponent(Map<Integer, List<Integer>> graph){
            Map<Integer, Boolean> visited = new HashMap<>();
            int max = 0;
            for (Integer key : graph.keySet()){
                int size = exploreSize(graph, key, visited);
                max = Math.max(max, size);

            }

            return max;
        }
        
        public static int shortestPath(Map<String, List<String>> graph, String source, String dest, Map<String, Boolean> visited){
            if (visited.containsKey(source)) return 0;
            if (source.equals(dest)) return 0;

            visited.put(source, true);
            int path = 0;

            for(String neighbour : graph.get(source)){
                int p = shortestPath(graph, neighbour, dest, visited) + 1;
                if(path == 0 || p < path) path = p;
            }

            return path;
        }
        private static boolean exploreLand(String[][] area, int row, int col, Map<String, Boolean> visited) {
            if(!(row >= 0 && row < area.length && col >= 0 && col < area[row].length)) return false;
            if(area[row][col].equals("W")) return false;
            
            String pos = row+","+col;
            if(visited.containsKey(pos)) return false;
            
            visited.put(pos, true);
            
            exploreLand(area, row - 1, col, visited);
            exploreLand(area, row, col + 1, visited);
            exploreLand(area, row + 1, col, visited);
            exploreLand(area, row, col - 1, visited);
            
            return true;
        }
        public static int islandCount(String[][] area){
            Map<String, Boolean> visited = new HashMap<>();
            int count = 0;
            for (int i = 0; i < area.length; i++) {
                for (int j = 0; j < area[i].length; j++) {
                    if(exploreLand(area, i, j, visited)) {
                        count++;
                    }
                }
            }
            return count;
        }
        private static int exploreLandSize(String[][] area, int row, int col, Map<String, Boolean> visited) {
            if(!(row >= 0 && row < area.length && col >= 0 && col < area[row].length)) return 0;
            if(area[row][col].equals("W")) return 0;

            String pos = row+","+col;
            if(visited.containsKey(pos)) return 0;

            int size = 1;
            visited.put(pos, true);

            size += exploreLandSize(area, row - 1, col, visited);
            size += exploreLandSize(area, row, col + 1, visited);
            size += exploreLandSize(area, row + 1, col, visited);
            size += exploreLandSize(area, row, col - 1, visited);

            return size;
        }
        public static int minimumIsland(String[][] area){
            Map<String, Boolean> visited = new HashMap<>();
            int min = 0;
            for (int i = 0; i < area.length; i++) {
                for (int j = 0; j < area[i].length; j++) {
                    int size = exploreLandSize(area, i, j , visited);
                    if(size > 0 && (min == 0 || size < min)) min = size;
                }
            }
            return min;
        }



    }
    static class BFS {
        public static void traverseGraph(Map<String, List<String>> graph, String source){
            Queue<String> queue = new PriorityQueue<>();
            queue.add(source);

            while (!queue.isEmpty()){
                String current = queue.remove();
                System.out.println(current);
                queue.addAll(graph.get(current));
            }
        }

        public static boolean canReach(Map<String, List<String>> graph, String source, String destination){
            Queue<String> queue = new PriorityQueue<>();
            queue.add(source);
            while(!queue.isEmpty()){
                String current = queue.remove();
                if(current.equals(destination)) return true;
                queue.addAll(graph.get(current));
            }
            return false;
        }

        public static boolean undirectedPath(String[][] edges, String node1, String node2){
            Map<String, List<String>> graph = buildGraphFromEdges(edges);
            Map<String, Boolean> visited = new HashMap<>();
            return hasPathUndirected(graph, node1, node2, visited);
        }
        private static boolean hasPathUndirected(Map<String, List<String>> graph, String source, String dest, Map<String, Boolean> visited){
            Queue<String> queue = new PriorityQueue<>();
            queue.add(source);

            while (!queue.isEmpty()){
                String current = queue.remove();
                visited.put(current, true);
                if(current.equals(dest)) return true;
                List<String> unVisitedNeighbours = graph.get(current).stream().filter(str -> !visited.containsKey(str)).collect(Collectors.toList());
                queue.addAll(unVisitedNeighbours);
            }
            return false;
        }

        private static<T> void explore(Map<T, List<T>> graph, T source, Map<T, Boolean> visited){
            Queue<T> queue = new PriorityQueue<>();
            queue.add(source);

            while (!queue.isEmpty()){
                T current = queue.remove();
                visited.put(current, true);
                List<T> unVisitedNeighbours = graph.get(current).stream().filter(str -> !visited.containsKey(str)).collect(Collectors.toList());
                queue.addAll(unVisitedNeighbours);
            }
        }
        public static int connectedComponentsCount(Map<Integer, List<Integer>> graph){
            Map<Integer, Boolean> visited = new HashMap<>();
            int count = 0;
            for (Integer key : graph.keySet()){
                if(!visited.containsKey(key)) {
                    count++;
                    explore(graph, key, visited);
                }
            }

            return count;
        }

        public static<T> int exploreSize(Map<T, List<T>> graph, T source, Map<T, Boolean> visited){
            Queue<T> queue = new PriorityQueue<>(){
                @Override
                public boolean add(T t) {
                    return !this.contains(t) && super.add(t);
                }
            };
            queue.add(source);

            int size = 0;

            while (!queue.isEmpty()){
                T current = queue.remove();
                visited.put(current, true);
                List<T> unVisitedNeighbours = graph.get(current).stream().filter(str -> !visited.containsKey(str)).collect(Collectors.toList());
                queue.addAll(unVisitedNeighbours);
                System.out.println(current + " : " + unVisitedNeighbours);
                size++;
            }
            return size;
        }
        public static int largestComponent(Map<Integer, List<Integer>> graph){
            Map<Integer, Boolean> visited = new HashMap<>();
            int max = 0;
            for (Integer key : graph.keySet()){
                if(visited.containsKey(key)) continue;
                int size = exploreSize(graph, key, visited);
                max = Math.max(max, size);

            }

            return max;
        }

        static class Path implements Comparable<Path>{
            String path; int distance;
            public Path(String p, int d){this.path =p; this.distance = d;}

            @Override
            public int compareTo(Path o) {
                return path.compareTo(o.path);
            }
        }
        public static int shortestPath(Map<String, List<String>> graph, String source, String dest, Map<String, Boolean> visited){
            Queue<Path> queue = new PriorityQueue<>(){
                @Override
                public boolean add(Path t) {
                    return !this.contains(t) && super.add(t);
                }
            };
            queue.add(new Path(source, 0));
            while (!queue.isEmpty()){
                Path current = queue.remove();
                if(current.path.equals(dest)) return current.distance;
                visited.put(current.path, true);
                List<Path> unVisitedNeighbours = graph.get(current.path).stream()
                        .filter(str -> !visited.containsKey(str))
                        .map(s -> new Path(s, current.distance + 1)).collect(Collectors.toList());
                queue.addAll(unVisitedNeighbours);
            }
            return -1;
        }

        static class Land implements Comparable<Land>{
            String position; int row, col;
            public Land(String p, int row, int col){this.position =p; this.row = row;this.col = col;}

            @Override
            public int compareTo(Land o) {
                return position.compareTo(o.position);
            }

            @Override
            public String toString() {
                return "pos: " + position + ", row: "+ row+ ", col: " + col;
            }
        }
        private static boolean exploreLand(String[][] area, int row, int col, Map<String, Boolean> visited) {
            String pos = row+","+col;
            if(area[row][col].equals("W")) return false;
            if(visited.containsKey(pos)) return false;
            Queue<Land> queue = new PriorityQueue<>(){
                @Override
                public boolean add(Land t) {
                    return !this.contains(t) && super.add(t);
                }
            };
            queue.add(new Land(pos, row, col));

            while (!queue.isEmpty()){
                Land current = queue.remove();
                int currentRow = current.row, currentCol = current.col;
                visited.put(current.position, true);
                List<Land> landNeighbours = Stream.of(List.of(currentRow - 1, currentCol), List.of(currentRow, currentCol + 1),
                                List.of(currentRow + 1, currentCol), List.of(currentRow, currentCol - 1))
                        .filter(list -> {
                            int r = list.get(0);
                            int c = list.get(1);
                            return r >= 0 && r < area.length && c >= 0 && c < area[r].length
                                    && area[r][c].equals("L") && !visited.containsKey(r+","+c);
                        }).map(lst -> new Land(lst.get(0)+","+lst.get(1), lst.get(0), lst.get(1)))
                        .collect(Collectors.toList());
                queue.addAll(landNeighbours);
            }
            return true;
        }
        public static int islandCount(String[][] area){
            Map<String, Boolean> visited = new HashMap<>();
            int count = 0;
            for (int i = 0; i < area.length; i++) {
                for (int j = 0; j < area[i].length; j++) {
                    if(exploreLand(area, i, j, visited)) count++;
                }
            }
            return count;
        }

        private static int exploreLandSize(String[][] area, int row, int col, Map<String, Boolean> visited) {
            String pos = row+","+col;
            if(area[row][col].equals("W")) return 0;
            if(visited.containsKey(pos)) return 0;
            Queue<Land> queue = new PriorityQueue<>(){
                @Override
                public boolean add(Land t) {
                    return !this.contains(t) && super.add(t);
                }
            };
            queue.add(new Land(pos, row, col));

            int size = 0;
            while (!queue.isEmpty()){
                Land current = queue.remove();
                int currentRow = current.row, currentCol = current.col;
                visited.put(current.position, true);
                size++;
                List<Land> landNeighbours = Stream.of(List.of(currentRow - 1, currentCol), List.of(currentRow, currentCol + 1),
                                List.of(currentRow + 1, currentCol), List.of(currentRow, currentCol - 1))
                        .filter(list -> {
                            int r = list.get(0);
                            int c = list.get(1);
                            return r >= 0 && r < area.length && c >= 0 && c < area[r].length
                                    && area[r][c].equals("L") && !visited.containsKey(r+","+c);
                        }).map(lst -> new Land(lst.get(0)+","+lst.get(1), lst.get(0), lst.get(1)))
                        .collect(Collectors.toList());
                queue.addAll(landNeighbours);
            }
            return size;
        }
        public static int minimumIsland(String[][] area){
            Map<String, Boolean> visited = new HashMap<>();
            int min = 0;
            for (int i = 0; i < area.length; i++) {
                for (int j = 0; j < area[i].length; j++) {
                    int size = exploreLandSize(area, i, j, visited);
                    if(size > 0 && (min == 0 || size < min)) min = size;
                }
            }
            return min;
        }


    }

    public static void main(String[] args) {
        String[][] area = new String[][]{
                {"W", "L", "W", "W", "L", "W"},
                {"L", "L", "W", "W", "L", "W"},
                {"W", "L", "W", "W", "W", "W"},
                {"W", "W", "W", "L", "L", "W"},
                {"W", "L", "W", "L", "L", "W"},
                {"W", "W", "W", "W", "W", "W"},
        };
        System.out.println(BFS.minimumIsland(area));
        
//        String[][] edges = new String[][]{{"i", "j"}, {"k", "i"}, {"m", "k"}, {"k", "l"}, {"o", "n"}};
//        System.out.println(BFS.shortestPath(buildGraphFromEdges(edges), "i", "k", new HashMap<>()));

//        System.out.println(BFS.largestComponent(buildGraphLargest()));
//
//        String[][] edges = new String[][]{{"i", "j"}, {"k", "i"}, {"m", "k"}, {"k", "l"}, {"o", "n"}};
//        System.out.println(BFS.undirectedPath(edges, "i", "l"));

//        boolean b = BFS.canReach(getGraph(), "a", "e");
//        System.out.println(b);

    }
}
