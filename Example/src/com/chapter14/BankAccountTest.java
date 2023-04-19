package com.chapter14;

public class BankAccountTest {

	public static void main(String[] args) {

		BankAccount a = new BankAccount("4649", 1592);
		BankAccount b = new BankAccount("   4649  ", 1592);
		BankAccount c = new BankAccount("4611", 2492);
		BankAccount d = new BankAccount("   46   49  ", 1592);
		System.out.println(a);
		
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.equals(d));
	}

}
