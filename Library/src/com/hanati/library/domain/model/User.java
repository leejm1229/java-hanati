package com.hanati.library.domain.model;

import java.time.LocalDate;
import java.util.List;

import com.hanati.library.data.UserCsvFileData;

public class User {
	private List<User> userList; // 모든 유저
	private User deleteUser;
	private int id; // 유저 id(autoincrement로 지정)
	private String name; // 이름
	private LocalDate signUpDay; // 가입날짜
	private String address; // 주소
	private String phoneNumber; // 전화번호
	private LocalDate birthday; // 생년월일
	private int age; // 나이

	public User() {
		userList = UserCsvFileData.loadUser();
		setUserList(userList);
	}

	public User(int id, String name, LocalDate signUpDay, String address, String phoneNumber, LocalDate birthday,
			int age) {
		this.id = id;
		this.name = name;
		this.signUpDay = signUpDay;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.age = age;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
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

	public User getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(User deleteUser) {
		this.deleteUser = deleteUser;
	}

	@Override
	public String toString() {

		return "ID = " + id + ", 이름 = " + name + ", 가입일자 = " + signUpDay + ", 주소 = " + address + ", 전화번호 = "
				+ phoneNumber + ", 생일 = " + birthday + ", 나이 = " + age;
	}

}