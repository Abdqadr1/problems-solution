package com.qadr.leetcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FallingSquares {
    public static Integer findTallest(Map<Integer, Integer> map){
        Integer max = 0;
        for (Integer height : map.values()){
            if(height > max) max = height;
        }
        return max;
    }
    public static int findBlockInYAxis(Map<Integer, Integer> map,int[][] position, int index){
        int result = -1;
        int pos = -1;
        int xStart = position[index][0], xEnd = xStart + position[index][1];
        for (Integer i : map.keySet()){
            int x1 = position[i][0], x2 = x1 + position[i][1];
            int height = map.get(i);
            if(((xStart == x1 && xEnd == x2) ||
                (xStart >= x1 && xStart < x2) || (xEnd > x1 && xEnd <= x2) ||
                (x1 >= xStart && x1 < xEnd) || (x2 > xStart && x2 <= xEnd)) && height > result
            ) {
                 result = height;
                 pos = i;
            }
        }
        return pos;
    }
    public static List<Integer> fallingSquares(int[][] positions) {
        Map<Integer, Integer> fell = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int sideLength = positions[i][1];
            int pos = findBlockInYAxis(fell, positions, i);
            if(pos != -1){
                int h = fell.get(pos) + sideLength;
                fell.put(i, h);
            }else{
                fell.put(i, sideLength);
            }
            Integer tallest = findTallest(fell);
            result.add(i, tallest);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(FallingSquares.fallingSquares(new int[][]{{1,2},{2,3},{6,1}}));

    }
}
