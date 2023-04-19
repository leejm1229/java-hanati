package com.chapter10.test;

import org.junit.Test;

import com.chapter10.Bank;

public class BankTest {

	@Test
	public void test() {
		Bank bank = new Bank("하나은행");
		System.out.println(bank.getName());
		
		bank.setName("하나금융티아이");
		System.out.println(bank.getName());
	}

}
