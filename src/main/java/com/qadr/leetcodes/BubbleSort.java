import java.util.Random;

public class BubbleSort {
	
	private static void bubbleSort(int[] array) {
		boolean swapSomething = true;
		while(swapSomething) {
			swapSomething = false;
			for(int i =0;i<array.length -1; i++) {
				if(array[i] > array[i + 1]) {
					swapSomething = true;
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
			}
		}
	}

		private static void printArray(int[] arr) {
			if(isSorted(arr)) {
				System.out.println("Sorted array");
			}else {
				System.out.println("Unsorted array");
			}
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
		int[] arr = new int[1000000];
		
		for(var i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10000);
		}
		System.out.println("Before: ");
		printArray(arr);
		long before = System.currentTimeMillis();
		
		bubbleSort(arr);
		
		long after = System.currentTimeMillis();
		System.out.println("After: ");
		printArray(arr);
		
		
		System.out.println("Timetaken: " + (after-before));
	}
	
}
