package com.qadr.leetcodes;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TrapRainWater {
    static class Height{
        int row, col, height;
        public Height(int row, int col, int height){
            this.col = col;
            this.row = row;
            this.height = height;
        }
    }

    public void addBoundaries(int[][] heightMap, Queue<Height> queue, int rows, int cols, boolean[][] visited){
        for (int i = 0; i < rows; i++) {
            // add first column
            queue.add(new Height(i, 0, heightMap[i][0]));
            visited[i][0] = true;

            // add last column
            queue.add(new Height(i, cols - 1, heightMap[i][cols-1]));
            visited[i][cols-1] = true;
        }

        for (int i = 1; i < cols-1; i++) {
            // add first column
            queue.add(new Height(0, i, heightMap[0][i]));
            visited[0][i] = true;

            // add last column
            queue.add(new Height(rows-1, i, heightMap[rows-1][i]));
            visited[rows-1][i] = true;
        }
    }
    public boolean isSafeMove(int r, int c, int rows, int cols, boolean[][] visited){
        return r>=0 && r<rows && c>=0 && c<cols && !visited[r][c];
    }

    public  int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 3) return 0;
        int trapped = 0;
        int[][] DIRS = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        int rows= heightMap.length, cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Height> heightQueue = new PriorityQueue<>(Comparator.comparingInt(i -> i.height));
        addBoundaries(heightMap,heightQueue, rows, cols, visited);

        Height current;
        while (!heightQueue.isEmpty()){
            current = heightQueue.poll();
            if(current != null){
                for (int[] dir : DIRS){
                    int r = current.row + dir[0];
                    int c = current.col + dir[1];
                    if(!isSafeMove(r, c, rows, cols, visited)) continue;
                    int h = heightMap[r][c];
                    visited[r][c] = true;

                    if(current.height > h){
                        trapped += (current.height - h);
                        heightQueue.add(new Height(r, c, current.height));
                    }else{
                        heightQueue.add(new Height(r, c, h));
                    }
                }
            }
        }

        return trapped;
    }

    public static void main(String[] args) {
        System.out.println(new TrapRainWater().trapRainWater(new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}}));
        System.out.println(new TrapRainWater().trapRainWater(new int[][]{{12, 13, 1, 12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}}));

    }
}
