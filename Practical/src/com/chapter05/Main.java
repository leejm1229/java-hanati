package com.chapter05;

interface Func1 {
	public abstract boolean isOdd(int n);
}

interface Func2 {
	public abstract String addNamePrefix(boolean male, String name);
}

public class Main {
	public static void main(String[] args) {

		Func1 function1 = n -> n % 2 == 1;
		Func2 function2 = (male, name) -> {
			if (male == true) {
				return "Mr." + name;
			}
			return "Ms." + name;
		};

		System.out.println(function1.isOdd(5));
		System.out.println(function2.addNamePrefix(true, "이정민"));
	}
}