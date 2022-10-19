package com.qadr.leetcodes;

import java.util.Random;

public class MergeSort {
	
	private static void mergeSort(int[] array) {
		int len = array.length;
		if(len < 2) return;
		int middleIndex = len / 2;
		int[] rightHalf = new int[middleIndex], leftHalf = new int[len - middleIndex];
		
		for(int i = 0; i < middleIndex; i++) {
			rightHalf[i] = array[i];
		}
		for(int j = middleIndex; j < len; j++) {
			leftHalf[j - middleIndex] = array[j];
		}
		
		mergeSort(rightHalf);
		mergeSort(leftHalf);
		
//		merge the 2 arrays
		merge(array, rightHalf, leftHalf);

		
	}

	private static void merge(int[] array, int [] rightHalf, int[] leftHalf) {
		int i = 0, j=0, k=0;
		int rightSize = rightHalf.length, leftSize = leftHalf.length;
		while(i < rightSize && j < leftSize) {
			if(rightHalf[i] <= leftHalf[j]) {
				array[k] = rightHalf[i];
				i++;
			}else {
				array[k] = leftHalf[j];
				j++;
			}
			k++;
		}
		while(i < rightSize) {
			array[k++] = rightHalf[i++];
		}
		while(j < leftSize) {
			array[k++] = leftHalf[j++];
		}
	}
	

	private static void printArray(int[] arr) {
		for(int i : arr) {
			System.out.println(i);
		}
//		if(isSorted(arr)) {
//			System.out.println("Sorted array");
//		}else {
//			System.out.println("Unsorted array");
//		}
	}
	
	private static boolean isSorted(int[] array) {
		int prev = array[0];
		for(int i=1; i<array.length;i++) {
			if(prev > array[i]) return false;
			prev = array[i];
		}
		return true;
	}
	
	public static void main (String[] args) {
		Random rand = new Random();
		int[] arr = new int[100000000];
		
		for(var i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10000);
		}
		System.out.println("Before: ");
//		printArray(arr);
		long before = System.currentTimeMillis();
		
		mergeSort(arr);
		
		long after = System.currentTimeMillis();
		System.out.println("After: ");
//		printArray(arr);
		
		
		System.out.println("Timetaken: " + (after-before));
	}


}
