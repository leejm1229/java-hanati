package com.chapter02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, Integer> personMap = new HashMap<>();
		Person person1 = new Person("홍길동");
		Person person2 = new Person("한석봉");

		List<Person> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);

		for (Person person : personList) {
			System.out.println(person.name);
		}

		personMap.put(person1.name, 20);
		personMap.put(person2.name, 25);

		for (Map.Entry<String, Integer> entry : personMap.entrySet()) {
			System.out.println(entry.getKey() + "의 나이는 " + entry.getValue() + "살");
		}

	}

}
