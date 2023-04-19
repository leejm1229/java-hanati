package com.chapter10.test;

import org.junit.Test;

import com.chapter10.DownCounter;

public class DownCounterTest {

	@Test
	public void test() {
		DownCounter down = new DownCounter();

		down.setCount(5);
		System.out.println(down.getCount());
		down.downCounter();
		System.out.println(down.getCount());
	}

}
