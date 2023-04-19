package com.chapter10.test;

import org.junit.Test;

import com.chapter10.UpCounter;

public class UpCounterTest {

	@Test
	public void test() {
		UpCounter up = new UpCounter();
		System.out.println(up.getCount());
		up.upCounter();
		System.out.println(up.getCount());
	}
}
