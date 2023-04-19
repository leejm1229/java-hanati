package com.chapter11;

import java.util.Scanner;

class PrintingThread implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
}

public class Main {
	int a = 0;
	int b = 0;

	void syncExam() {
		System.out.println("시작");

		synchronized (this) {
			a += 2;
			b = a * 4;
		}
		System.out.println("끝");
	}
	
	
}
