package com.chapter15;

public class Person {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age < 0) {
			throw new IllegalArgumentException("나이가 음수가 될 수 없음. 지정한 값=" + age);
		}
		this.age = age; // 문제 없을 경우, 필드에 값을 설정 
	}

}

