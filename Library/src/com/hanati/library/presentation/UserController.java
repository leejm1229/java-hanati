package com.hanati.library.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.data.UserCsvFileData;
import com.hanati.library.data.UserRepositoryImpl;
import com.hanati.library.domain.model.User;
import com.hanati.library.domain.repository.UserRepository;

public class UserController {
	private UserRepository userRepository = new UserRepositoryImpl();
	Scanner scanner = new Scanner(System.in);
	User user = new User();

	public UserController() {

		while (true) {

			System.out.println("0.뒤로 \t 1.회원조회 \t 2.회원등록 \t 3.회원수정 \t 4.회원삭제 \t 5.회원복구");
			int num = scanner.nextInt();
			if (num == 1) {
				printUserList();
			} else if (num == 2) {
				addUser();
			} else if (num == 3) {
				updateUser();
			} else if (num == 4) {
				deleteUser();
			} else if (num == 5) {
				rollback();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	public void printUserList() {
		List<User> tmpList = UserCsvFileData.loadUser();
		System.out.println(tmpList);
		System.out.println(user.getDeleteUser());
	}

	public void addUser() {
		List<User> tmpList = user.getUserList();

		System.out.println("유저 id를 입력하세요.");
		int id = scanner.nextInt();

		for (User tmp : tmpList) {
			if (tmp.getId() == id) {
				System.out.println("이미 존재하는 id입니다.");
			}
		}

		System.out.println("이름을 입력하세요.");
		String name = scanner.next();

		// 현재 날짜 받기
		LocalDate signUpDay = LocalDate.now();

		scanner.nextLine();
		System.out.println("주소를 입력하세요.");
		String address = scanner.nextLine();

		System.out.println("전화번호를 입력하세요.(000-0000-0000)");
		String phoneNumber = scanner.nextLine();

		System.out.println("생일을 입력하세요.(yyyy/MM/dd)");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String input = scanner.next();
		LocalDate birthday = LocalDate.parse(input, formatter);

		int age = (int) ChronoUnit.YEARS.between(birthday, signUpDay) + 1;

		user = new User(id, name, signUpDay, address, phoneNumber, birthday, age);

		tmpList.add(user);
		user.setUserList(tmpList);

		userRepository.addUser(user);
	}

	public void updateUser() {
		System.out.println("수정할 회원의 id를 입력하세요.");
		int id = scanner.nextInt();
		userRepository.updateUser(user, id);
	}
	
	public void deleteUser() {
		System.out.println("삭제할 회원의 id를 입력하세요.");
		int id = scanner.nextInt();
		userRepository.deleteUser(user, id);
	}
	
	public void rollback() {
		userRepository.rollbackUser(user);
	}
}
