package com.chapter10;

public class DownCounter implements Counter {
	private int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void downCounter() {
		this.setCount(this.getCount() - 1);
	}

	@Override
	public int count() {
		return getCount();
	}
}
