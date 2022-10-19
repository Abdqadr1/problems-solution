package com.qadr.leetcodes;

public class Fibonnaci {
	private static long[] fibonnaciCache;
	private static long fibonnaci(int n) {
		if(n <= 1) return n;
		
		// memoization
		if(fibonnaciCache[n] != 0) {
			return fibonnaciCache[n];
		}
		
		long nthFibonnaciNumber = fibonnaci(n-2) + fibonnaci(n-1);

		System.out.print(nthFibonnaciNumber + " ");
		fibonnaciCache[n] = nthFibonnaciNumber;
		return nthFibonnaciNumber;
	}

	public static void main (String[] args) {
		int n = 92;
		fibonnaciCache = new long[n+1];
		long before = System.currentTimeMillis();
		
		fibonnaci(n);
		
		long after = System.currentTimeMillis();
		
		System.out.println("Timetaken: " + (after-before));
	}
	

}
