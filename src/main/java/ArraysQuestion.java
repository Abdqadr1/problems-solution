import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArraysQuestion {
    public int[] searchRange(int[] nums, int target) {
        int[] results = new int[] {-1,-1};
        for (int i = 0; i < nums.length; i++) {
            int in = nums[i];
            if(in != target) continue;
            if (results[0] == -1) {
                results[0] = i;
            }
            results[1] = i;
        }
        return results;
    }
    public int search(int[] nums, int target) {
        int left =0, right=nums.length-1;
        while(left <= right){
            if(nums[left] == target) return left;
            if(nums[right] == target) return right;
            left++;
            right--;
        }
        return -1;
    }
    public boolean exist(char[][] board, String word) {
        int[][] DIRS = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char ch = board[i][j];
                if(ch == word.charAt(0)){
                    visited[i][j] = true;
                    if(checkNeighbours(i, j, board, word, 1, DIRS, visited)) return true;
                    else visited[i][j] = false;
                }
            }
        }
        return false;
    }
    public boolean checkNeighbours(int row, int col, char[][] board, String word, int charIndex, int[][] dirs, boolean[][] visited){
        if(charIndex == word.length()) return true;

        for(int[] dir : dirs){
            int r = row + dir[0];
            int c = col + dir[1];
            if(r >=0 && r < board.length && c>=0 && c < board[r].length &&
                    !visited[r][c] && board[r][c] == word.charAt(charIndex)){
                visited[row][col] = true;
                if(checkNeighbours(r, c, board, word, charIndex+1, dirs, visited)) return true;
                else visited[r][c] = false;
            }
        }
        return false;
    }
    public int numIslands(char[][] grid) {
        int row = grid.length, col = grid[0].length;
        int count =0;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    exploreLand(i, j, grid, visited);
                }

            }
        }
        return count;
    }
    public void exploreLand(int row, int col, char[][] grid, boolean[][] visited){
        int[][] DIRS = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        visited[row][col] = true;
        for (int[] dir : DIRS){
            int r = row+dir[0];
            int c = col+dir[1];
            if(r>=0 && r < grid.length && c >=0 && c < grid[r].length && grid[r][c] == '1' && !visited[r][c]){
                exploreLand(r, c, grid, visited);
            }
        }

    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startIndex = 0;
        while(startIndex < gas.length){
            int tank = gas[startIndex] - cost[startIndex];
            int nextStation = startIndex+1;
            while(tank >= 0){
                int index = nextStation % gas.length;
                if(index == startIndex) return startIndex;

                tank += gas[index] - cost[index];

                nextStation++;
            }
            startIndex = nextStation;
        }
        return -1;
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, result, new ArrayList<>(), 0);
        return result;
    }
    public void combinationSum(int[] candidates, int target, List<List<Integer>> result, List<Integer> list, int start) {
        if(target==0) result.add(new ArrayList<>(list));

        for (int i=start;i<candidates.length;i++){
            int candidate = candidates[i];
            if(candidate <= target){
                int rem = target - candidate;
                list.add(candidate);
                combinationSum(candidates, rem, result, list, i);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new ArraysQuestion().combinationSum(new int[]{2,3,5}, 8));
    }
}
