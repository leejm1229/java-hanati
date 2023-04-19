package com.chapter14;

import java.util.Objects;

public class BankAccount {

	private String accountNumber;
	private int balance;

	BankAccount(String accountNumber, int balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return this.getBalance() + "원(계좌번호=" + getAccountNumber() + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(accountNumber.trim(), other.accountNumber.trim());
	}

}
