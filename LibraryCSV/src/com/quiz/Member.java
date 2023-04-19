package com.quiz;

import java.time.LocalDate;
import java.util.Date;

public class Member {
	private int memberId; // 멤버 id
	private String name; // 이름
	private LocalDate signUpDay; // 가입날짜
	private String address; // 주소
	private String phoneNumber; // 전화번호
	private LocalDate birthday; // 생년월일
	private int age; // 나이

	Member(int memberId, String name, LocalDate signUpDay,
		   String address, String phoneNumber, LocalDate birthday, int age) {
		this.memberId = memberId;
		this.name = name;
		this.signUpDay = signUpDay;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.age = age;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getSignUpDay() {
		return signUpDay;
	}

	public void setSignUpDay(LocalDate signUpDay) {
		this.signUpDay = signUpDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
