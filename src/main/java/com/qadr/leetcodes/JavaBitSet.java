package com.qadr.leetcodes;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaBitSet {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        BitSet set1 = new BitSet(n);
        BitSet set2 = new BitSet(n);
        Pattern pattern = Pattern.compile("[A-Z]+");
        while(M-- > 0) {
        	String op = sc.next(pattern.toString());
        	int set = sc.nextInt();
        	int on = sc.nextInt();
        	switch(op) {
        		case "AND":
        			if(set == 1) set1.and(set2);
        			else set2.and(set1);
                	System.out.println(set1.cardinality() + " " + set2.cardinality());
        			break;
        		case "XOR":
        			if(set == 1) set1.xor(set2);
        			else set2.xor(set1);
                	System.out.println(set1.cardinality() + " " + set2.cardinality());
        			break;
        		case "OR":
        			if(set == 1) set1.or(set2);
        			else set2.or(set1);
                	System.out.println(set1.cardinality() + " " + set2.cardinality());
        			break;
        		case "FLIP":
        			if(set == 1) set1.flip(on);
        			else set2.flip(on);
                	System.out.println(set1.cardinality() + " " + set2.cardinality());
        			break;
        		case "SET":
        			if(set == 1) set1.set(on);
        			else set2.set(on);
                	System.out.println(set1.cardinality() + " " + set2.cardinality());
        			break;
        		default:
        			System.out.println("No handle for "+op);
        	}
        }
        sc.close();
    }
}