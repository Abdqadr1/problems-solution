package com.qadr.leetcodes;

import java.util.Arrays;

class Fall {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int rows = grid.length;
        int columns = grid[0].length;

        //Initialise new array which showing the areas that can be merged
        int[][] mergeAreas = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                mergeAreas[i][j] = grid[i][j];
            }
        }

        //Set the area where bricks have fallen to unmergeable
        for(int[] hit: hits){
            mergeAreas[hit[0]][hit[1]] = 0;
        }

        //Create the holder for the root and every section is its own root initially
        //We will provision an extra space for the root node (the last brick space)
        int[] root = new int[rows * columns + 1];
        for(int i = 0; i < root.length; i++){
            root[i] = i;
        }

        //Also set the size for the sections which we can assume as 1 initially.
        int[] size = new int[rows * columns + 1];
        Arrays.fill(size, 1);

        //Now we will group each region so we can get the size of the bricks
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                //Merge only in the mergeable regions also since we are traversing
                //top to bottom, left to right we only have to care if there are prior
                //bricks on top or towards the left
                if(mergeAreas[i][j] == 1){
                    //The index of the current brick is its row * bricks per row + col no
                    int index = i * columns + j;
                    //If bricks are connected to roof
                    if(i == 0){
                        union(root, size, index, rows * columns);
                    }

                    if(i > 0 && mergeAreas[i-1][j] == 1){
                        union(root, size, index, index-columns);
                    }

                    //we will need multiple if statements to be able to form
                    //the proper connection as a brick can be connected to both
                    //regions from left and top not just either or
                    if(j > 0 && mergeAreas[i][j-1] == 1){
                        union(root, size, index, index-1);
                    }
                }
            }
        }

        int[] toret = new int[hits.length];
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        //Calculate the bricks fallen per hit
        for(int i = hits.length-1; i >= 0; i--){
            int hitRow = hits[i][0];
            int hitCol = hits[i][1];

            //Initial size of brick connections to roof
            int initialSize = size[find(root, rows * columns)];

            //We only care if there is a brick struck down in the first place
            //If the strike is to a empty area then disregard as no connections will be formed
            //Or additional bricks created.
            if(grid[hitRow][hitCol] == 1){
                int index = hitRow * columns + hitCol;

                //We need to merge the current brick with all the surrounding bricks
                for(int[] dir: directions){
                    int searchRow = hitRow + dir[0];
                    int searchCol = hitCol + dir[1];
                    if(0 <= searchRow && 0 <= searchCol &&  searchRow < rows && searchCol < columns && mergeAreas[searchRow][searchCol] == 1){
                        union(root, size, searchRow * columns + searchCol, index);
                    }
                    //Also if the brick that is hit is connected to the roof, we perform
                    //another connection to thte roof
                    if(hitRow == 0){
                        union(root, size, index, rows * columns);
                    }
                    //Also set region as mergeable
                    mergeAreas[hitRow][hitCol] = 1;
                    //math.max since the added struck brick might not cause changes to
                    //the number of bricks connected to the roof yet since, there
                    //could be anoher brick that leads to its connection.
                    toret[i] = Math.max(0, size[find(root, rows * columns)] - initialSize -1);
                }
            }
        }

        return toret;
    }

    public int find(int[] root, int index){
        while(root[index] != root[root[index]]){
            root[index] = root[root[index]];
            index = root[index];
        }
        return root[index];
    }

    public void union(int[] root, int[] size, int indexOne, int indexTwo){
        int rootOne = find(root, indexOne);
        int rootTwo = find(root, indexTwo);

        if(rootOne == rootTwo){
            return;
        }

        //Since now rootTwo becomes the parent node
        root[rootOne] = rootTwo;
        size[rootTwo] = size[rootTwo] + size[rootOne];
    }

}

class UnionFind{
    private final int[][] grid;
    int m, n;
    int[] parents, size;
    int[][] DIRS = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};

    public UnionFind(int[][] grid){
        this.m = grid.length;
        this.n = grid[0].length;
        this.parents = new int[m*n+1];
        this.size = new int[m*n+1];
        this.grid = grid;

        this.parents[m*n] = m * n;
        this.size[m*n] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                this.size[index] = 1;
                parents[index] = index;
            }
        }
    }
    public int findParent(int index){
        if(parents[index] != index){
            parents[index] = findParent(parents[index]);
        }
        return parents[index];
    }
    public void union(int[] parents, int index1, int index2){
        int parent1 = findParent(index1);
        int parent2 = findParent(index2);
        int root = m * n;

        if(parent1 == parent2) return;

        if(parent1 == root || parent2 == root){
            if(parent2 == root){
                parents[parent1] = parent2;
                size[parent2] += size[parent1];
            } else {
                parents[parent2] = parent1;
                size[parent1] += size[parent2];
            }
        } else {
            if(parent2 < parent1){
                parents[parent1] = parent2;
                size[parent2] += size[parent1];
            } else {
                parents[parent2] = parent1;
                size[parent1] += size[parent2];
            }
        }

    }

    public void unionNeighbours(int row, int col){
        int index1 = row * n + col;

        for (int[] dir : DIRS){
            int r = row + dir[0];
            int c = col + dir[1];
            if(r >= 0 && r < m && c>= 0 && c < n && grid[r][c] == 1){
                int index2 = r * n + c;
                union(parents, index1, index2);
            }
        }

        if(row == 0){
            union(parents, index1, m*n);
        }
    }
}
class BrickFall {
    private static void printArray(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    private static void printArray(int[][] arr) {
        for(int[] i : arr) {
            System.out.println(Arrays.toString(i));
        }
    }
    public static int[] hitBricks(int[][] grid, int[][] hits){
        int[] result = new int[hits.length];
        int m = grid.length, n = grid[0].length;
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1)
                    uf.unionNeighbours(i, j);
            }
        }
        int currentSize = uf.size[m*n];

        for (int i = hits.length-1; i >= 0; i--) {
            int[] hit = hits[i];
            int row = hit[0], col = hit[1];
            int hitCell = grid[row][col];
            if(hitCell == 2){
                grid[hit[0]][hit[1]] = 1;
                uf.unionNeighbours(row, col);
                int size = uf.size[m*n];
                if(size > currentSize){
                    result[i] = size - currentSize - 1;
                    currentSize = size;
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] grid = {{1,0,0,0},{1,1,0,0}}, hits = {{1,1},{1,0}};
//        int[][] grid = {{1,0,1},{1,1,1}}, hits = {{0,0},{0,2},{1,1}};
//        int[][] grid = {{1},{1},{1},{1},{1}},hits = {{3,0},{4,0},{1,0},{2,0},{0,0}};
        int[][] grid = {{1,0,0,0},{1,1,1,0}}, hits = {{1,0}};

//        int[][] grid = {{0,1,1,1,1,1},{1,1,1,1,1,1},{0,0,1,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}},
//                hits = {{1,3},{3,5},{0,3},{3,3},{1,1},{4,2},{1,0},{3,0},{4,5},{2,1},{4,4},{4,0},{2,4},{2,5},{3,4},{0,5},{0,4},{3,2},{1,5},{4,1},{2,2},{0,2}};
        System.out.println("before: ");
        int[] result = hitBricks(grid, hits);
        printArray(result);

//        Integer[][] edges = {{1,2},{3,4}, {5,6}, {7,8}, {2,4}, {2,5}, {1,3}, {6,8}, {5,7}};
//        detectCycle(edges);

    }
}