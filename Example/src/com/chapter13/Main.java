package com.chapter13;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);  // Autoboxing
		int i = list.get(0);  // Autounboxing
	}
}
