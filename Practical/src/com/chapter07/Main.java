package com.chapter07;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable {
	private String name;
	private int age;

	Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

class Department implements Serializable {
	String name;
	Employee leader;

	Department(String name, Employee leader) {
		this.name = name;
		this.leader = leader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}

}

public class Main {
	public static void main(String[] args) throws IOException {
		Employee leader = new Employee("홍길동", 41);
		Department department = new Department("총무부", leader);

		FileOutputStream fos = new FileOutputStream("company.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(department);
		oos.flush();
		oos.close();
	}
}
