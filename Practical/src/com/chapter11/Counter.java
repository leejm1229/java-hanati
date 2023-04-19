package com.chapter11;

public class Counter {
	private long count = 0;

	public void add(long i) {
		System.out.println("더하기");
		this.count += 1;
	}

	public void mul(long i) {
		System.out.println("곱하기");
		this.count *= i;
	}

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		int num = 1;
		int num2 = 2;

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(10);
					synchronized (counter) {
						counter.add(num);
						counter.mul(num2);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		Thread.sleep(1000);
		System.out.println(counter.count);
	}
}
