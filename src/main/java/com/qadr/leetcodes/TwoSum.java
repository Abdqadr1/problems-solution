package com.qadr.leetcodes;

import java.util.*;

public class TwoSum {

//    a better way
    private static void findSumsBetter(int[] arr, int sum){
        Map<Integer, Integer> complements = new HashMap<>();
        complements.put(arr[0], 0);
        for(var i = 1; i< arr.length; i++){
            int element = arr[i];
            int complement = sum - element;
            if(complements.containsKey(complement)){
                System.out.println("The sums are : " + complement + " + " + element + " = " + sum);
            } else {
                complements.put(element, i);
            }
        }
    }
    private static int[] findSums(int[] nums, int target){
        int[] sums = new int[2];
        Map<Integer, Integer> complements = new HashMap<>();
        complements.put(nums[0], 0);
        for(var i = 1; i< nums.length; i++){
            int num = nums[i];
            int complement = target - num;
            if(complements.containsKey(complement)){
                int c = complements.get(complement);
                return new int[]{i, c};
            }
            complements.put(num, i);
        }
        return sums;
    }

    public static int reverseInteger(int x) {
        String str = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try{
            return x < 0 ? Integer.parseInt(str) * -1 : Integer.parseInt(str);
        }catch (NumberFormatException e) {
            return 0;
        }
    }
    public static int myAtoi(String s) {
        s = s.trim();
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        int rightPointer = 0, leftPointer = 0;
        int sign = 1;
        for (int i = 0; i < chars.length; i++){
            char ch = chars[i];
            rightPointer++;
            if(i==0 && ch == 45){
                sign = -1;
                leftPointer++;
                continue;
            }
            if(i==0 && ch == 43) {
                leftPointer++;
                continue;
            }
            if(!(ch >= 48 && ch <= 57)) {
                rightPointer--;
                break;
            }
        }
        String str = s.substring(leftPointer, rightPointer);
        try {
            return str.isEmpty() ? 0 : Integer.parseInt(str) * sign;
        } catch (NumberFormatException e){
            return sign < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }
    public static int maxArea(int[] height) {
        int max = 0;
        int leftPointer = 0, rightPointer= height.length-1;

        for (int i = 0; i < height.length; i++) {
            int h1 = height[leftPointer];
            int h2 = height[rightPointer];
            int area = (Math.min(h1, h2)) * (rightPointer - leftPointer);
            max = Math.max(max, area);

            if (h2 > h1) leftPointer++;
            else rightPointer--;
        }

        return max;
    }

    public static int trap(int[] height) {
        int trap = 0, leftPointer =0, rightPointer= height.length-1;
        while (leftPointer < rightPointer){
            if(height[leftPointer] <= height[rightPointer]){
                int current = height[leftPointer];
                while (height[++leftPointer] < current){
                    trap += current - height[leftPointer];
                }
            }else {
                int current = height[rightPointer];
                while (height[--rightPointer] < current){
                    trap += current - height[rightPointer];
                }
            }
        }
        return trap;
    }
    public static int trapRainWater(int[][] heightMap) {
        int count = 0;
        for (int i = 1; i < heightMap.length-1; i++) {
            int[] row = heightMap[i];
            count += trap(row);
        }
        return count;
    }
    class HeapEntry {
        int x;
        int y;
        int height;

        public HeapEntry(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public static void main(String[] args) {

//        int[] arr = {2,4,5,68, 46, 12, 9};
//        findSumsBetter(arr, 114);
//        System.out.println(reverseInteger(-153236469));
//        System.out.println(myAtoi("-42"));


//        System.out.println(findNeighbour(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 7));

    }
}
