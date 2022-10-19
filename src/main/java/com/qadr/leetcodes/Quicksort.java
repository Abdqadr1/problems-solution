package com.qadr.leetcodes;

import java.util.*;
public class Quicksort {
	private static void quickSort(int[] array) {
		quickSort(array, 0, array.length -1);
	}
	
	private static void quickSort(int[] array, int startIndex, int endIndex) {
		if(startIndex >= endIndex) return;
		
		int pivotIndex = new Random().nextInt(endIndex - startIndex) + startIndex;
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, endIndex);
		
		int leftPointer = partition(array, startIndex, endIndex, pivot);
		quickSort(array, startIndex, leftPointer - 1);
		quickSort(array, leftPointer + 1, endIndex);
	}

	private static int partition(int[] array, int startIndex, int endIndex, int pivot) {
		int leftPointer=startIndex, rightPointer=endIndex-1;
		
		while(leftPointer < rightPointer) {
			while(array[leftPointer] <= pivot && leftPointer < rightPointer) {
				leftPointer++;
			}
			while(array[rightPointer] >= pivot && leftPointer < rightPointer) {
				rightPointer--;
			}
			swap(array, leftPointer, rightPointer);
		}
		if(array[leftPointer] > array[endIndex]) {
			swap(array, leftPointer, endIndex);
		}else {
			leftPointer = endIndex;
		}
		return leftPointer;
	}
	
	private static boolean isSorted(int[] array) {
		int prev = array[0];
		for(int i=1; i<array.length;i++) {
			if(prev > array[i]) return false;
			prev = array[i];
		}
		return true;
	}
	
	private static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	private static void printArray(int[] arr) {
//		for(int i : arr) {
//			System.out.println(i);
//		}
		if(isSorted(arr)) {
			System.out.println("Sorted array");
		}else {
			System.out.println("Unsorted array");
		}
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr = new int[10];
		
		for(var i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10000);
		}
		System.out.println("Before: ");
		printArray(arr);
		long before = System.currentTimeMillis();
		quickSort(arr);
		long after = System.currentTimeMillis();
		System.out.println("After: ");
		printArray(arr);
		
		
		System.out.println("Timetaken: " + (after-before));
		
	}
}
