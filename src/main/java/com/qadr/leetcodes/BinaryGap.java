package com.qadr.leetcodes;

import java.util.Scanner;

public class BinaryGap {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String bitString = Integer.toBinaryString(n);
		System.out.println(bitString);
		String[] str = bitString.split("");
		int gap = 0, count = 0;
		boolean hasStartedCounting = false;
		for (String bitChar : str) {
			if (bitChar.equals("0")) {
				count++;
				hasStartedCounting = true;
			} else {
				if (hasStartedCounting) {
					gap = Math.max(gap, count);
				}
				count = 0;
			}

		}
		System.out.println(gap);
		sc.close();
	}

}
