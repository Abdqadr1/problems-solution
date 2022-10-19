package com.qadr.leetcodes;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaSubArray {
	static List<List<Integer>> findSubArrays (List<Integer> arr){
        List<List<Integer>> subarrays = new ArrayList<>();
        for (int i = arr.size();i > 0;i--) {
            for(int j=0;j < arr.size();j++){
            	List<Integer> each = new ArrayList<Integer>();
            	for(int x = j;x < i;x++) {
            		each.add(arr.get(x));
            	}
            	subarrays.add(each);
            }
        }
        System.out.println(subarrays);
        return subarrays;
    }

//    public static void main(String[] args) {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int negativeSum = 0;
//        List<Integer> arr = new ArrayList<>();
//        for (int i=0;i<n;i++) {
//            int x = sc.nextInt();
//            arr.add(x);
//        }
//        List<List<Integer>> subarrays = findSubArrays(arr);
//        for (List<Integer> list : subarrays){
//            int sum = 0;
//            for (Integer in : list){
//                sum += in;
//            }
//            if (sum < 0) {
//                negativeSum++;
//            }
//        }
//        System.out.println(negativeSum);
//        sc.close();
//    }
	
	  public static void main(String[] args) {
		  System.out.println(findSubArrays(List.of(3,5,7,6,3)));
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. 
	         * Your class should be named Solution. */
//	        Scanner sc = new Scanner(System.in);
//	        List<List<String>> arr = new ArrayList<>();
//	        int n = sc.nextInt(); sc.nextLine();
//	        while(n-- > 0){
//	           String[] line  = sc.nextLine().split(" ");
//	           arr.add(ArraysQuestion.asList(line));
//	        }
//	        int q = sc.nextInt();
//	        while(q-- > 0){
//	            int y = sc.nextInt() - 1;
//	            int x = sc.nextInt() - 1;
//	            try {
//		            System.out.println(arr.get(y).get(x));
//	            } catch (ArrayIndexOutOfBoundsException e) {
//	            	System.out.println("ERROR!");
//	            }
//
//	        }
//	        sc.close();
	    
	    }
}