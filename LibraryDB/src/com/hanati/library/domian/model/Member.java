package com.hanati.library.domian.model;

import java.util.List;

public class Member {
	private List<Member> memberList; // 모든 유저
	private Member deleteUser;
	private int id; // 유저 id
	private String name; // 이름
	private String signUpDay; // 가입날짜
	private String address; // 주소
	private String phoneNumber; // 전화번호
	private String birthday; // 생년월일
	private int age; // 나이

	public Member(String name, String address, String phoneNumber, String birthday) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;

	}

	public Member(int id, String name, String signUpDay, 
			String address, String phoneNumber, String birthday, int age) {
		this.id = id;
		this.name = name;
		this.signUpDay = signUpDay;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.age = age;
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignUpDay() {
		return signUpDay;
	}

	public void setSignUpDay(String signUpDay) {
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Member getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(Member deleteUser) {
		this.deleteUser = deleteUser;
	}

}
