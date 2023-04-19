package com.chapter10.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.chapter10.Account;

public class AccountTest {

	@Test
	public void test() {
		Account account = new Account("홍길동", 30000);
		assertEquals("홍길동", account.getOwner());
		assertEquals(30000, account.getBalance());
	}

	@Test
	public void transfer_test() {
		Account account = new Account("홍길동", 30000);
		Account account2 = new Account("한석봉", 0);
		
		account.transfer(account2, 10000);
		
		assertEquals(20000, account.getBalance());
		assertEquals(10000, account2.getBalance());
	}
}
