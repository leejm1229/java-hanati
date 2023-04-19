package com.chapter10;

public class AccountTest {

	public static void main(String[] args) {
		testInit();
	}

	public static void testInit() {
		System.out.println("==== 테스트 시작 ====");
		Account account = new Account("홍길동", 30000);
		if (!account.getOwner().equals("홍길동")) {
			System.out.println("이름이 다름");
		}
		if (30000 != account.getBalance()) {
			System.out.println("잔액이 다름");
		}
		System.out.println(account);
		System.out.println("==== 테스트 완료 ====");

		Account account2 = new Account("한석봉", 0);
		account.transfer(account2, Integer.MAX_VALUE + 1);
		
		if(account2.getBalance()!=214748364L) {
			System.out.println("getBalance()값이 다름.");
		}
	}
}
