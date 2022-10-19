package com.qadr.leetcodes;

import java.util.Random;

public class InsertionSort {
	
	private static void insertionSort(int[] array) {
		
		for(int i =1; i< array.length;i++) {
			int currentValue = array[i];
			int j = i-1;
			while(j >= 0 && array[j] > currentValue) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = currentValue;
		}
		
	}
	

	private static void printArray(int[] arr) {
		for(int i : arr) {
			System.out.println(i);
		}
	}
	
	public static void main (String[] args) {
		Random rand = new Random();
		int[] arr = new int[1000000];
		
		for(var i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10000);
		}
		long before = System.currentTimeMillis();
		insertionSort(arr);
		long after = System.currentTimeMillis();
		
		
		System.out.println("Timetaken: " + (after-before));
	}

}
