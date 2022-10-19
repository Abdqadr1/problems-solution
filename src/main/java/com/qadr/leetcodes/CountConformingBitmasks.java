package com.qadr.leetcodes;

import java.util.Scanner;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class CountConformingBitmasks {
    public static boolean conform(int A, int B){
        String[] bitStringA = Integer.toBinaryString(A).split("");
        String[] bitStringB = Integer.toBinaryString(B).split("");
//        System.out.println("A = "+Integer.toBinaryString(A) +
//        		" B = "+Integer.toBinaryString(B));
        for(int i=0;i<bitStringB.length;i++){
            String BbitChar = bitStringB[i];
            if(BbitChar.equals("0"))continue;
            String AbitChar = bitStringA[i];
            if(!BbitChar.equals(AbitChar)) return false;
        }
        return true;
    }
    public static int solution(int A, int B, int C) {
        // write your code in Java SE 8
        int count = 0, max = 1073741823, min = Integer.MAX_VALUE;
        if(min > A) min = A;
        if(min > B) min = B;
        if(min > C) min = C;
//        System.out.println("min "+min + " max "+ max);
        for(int i=min;i<=max;i++){
            if(conform(i, A) || conform(i, B) || conform(i, C)) count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner (System.in);
    	int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
    	System.out.println(solution(a,b, c));
    	sc.close();
    }
}