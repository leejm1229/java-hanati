package com.chapter10;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] total = new int[M];

		String[] arr = scanner.nextLine().split(" ");
//		int[] numbers = new int[arr.length];
//
//		for (int i = 0; i < arr.length; i++) {
//			numbers[i] = Integer.parseInt(arr[i]);
//
//		}
//
//		for (int i = 0; i < N; i++) {
//			int x = scanner.nextInt();
//			int y = scanner.nextInt();
//			for (int j = x + 1; j < y; j++) {
//				total[i] += numbers[j];
//			}
//		}
//		
//		for (int i = 0; i < total.length; i++) {
//			System.out.println(total[i]);
//		}
	}

}
