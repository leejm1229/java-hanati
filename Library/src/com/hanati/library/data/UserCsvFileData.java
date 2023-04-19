package com.hanati.library.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.domain.model.User;

public class UserCsvFileData {
	private static String userFileName = "User.csv";
	private static File file = new File(userFileName);

	public static ArrayList<User> loadUser() {
		ArrayList<User> userList = new ArrayList<>();
		try {
			// 파일 열고
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(userFileName), "EUC-KR"));

			// 첫 줄은 컬럼명 빼고
			String lines = br.readLine();

			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int id = Integer.parseInt(line[0]);
				String name = line[1];
				LocalDate signUpDay = LocalDate.parse(line[2]);
				String address = line[3];
				String phoneNumber = line[4];
				LocalDate birthday = LocalDate.parse(line[5]);
				int age = Integer.parseInt(line[6]);
				User user = new User(id, name, signUpDay, address, phoneNumber, birthday, age);
				userList.add(user);
			}
			br.close();
		} catch (IOException e) {
		}

		return userList;
	}

	public static void addUser(User user) {
		try {
			List<User> datas = user.getUserList();
			// 기존 파일 삭제
			file.delete();

			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(userFileName, true), "EUC-KR"));

			// 컬럼명 작성
			bw.write("Id,name,signUpDay,address,phoneNumber,birthday,age");
			bw.newLine();

			// 데이터 추가
			for (User data : datas) {
				bw.write(data.getId() + "," + data.getName() + "," + data.getSignUpDay() + "," + data.getAddress() + ","
						+ data.getPhoneNumber() + "," + data.getBirthday() + "," + data.getAge());
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			System.out.println("회원등록에 실패하였습니다.");
		}
	}

	public static void updateUser(User user, int id) {
		Scanner scanner = new Scanner(System.in);
		List<User> datas = user.getUserList();

		try {
			// 기존 파일 삭제
			file.delete();

			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(userFileName, true), "EUC-KR"));

			// 컬럼명 작성
			bw.write("Id,name,signUpDay,address,phoneNumber,birthday,age");
			bw.newLine();

			// 데이터 추가
			for (User data : datas) {
				if (data.getId() == id) {
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

					bw.write(id + "," + name + "," + signUpDay + "," + address + "," + phoneNumber + "," + birthday
							+ "," + age);
					bw.newLine();

				} else {
					bw.write(data.getId() + "," + data.getName() + "," + data.getSignUpDay() + "," + data.getAddress()
							+ "," + data.getPhoneNumber() + "," + data.getBirthday() + "," + data.getAge());
					bw.newLine();
				}

			}

			bw.close();
		} catch (IOException e) {
			System.out.println("회원 업데이트에 실패하였습니다.");
		}
	}

	public static void deleteUser(User user, int id) {
		List<User> datas = user.getUserList();
		List<User> temp = new ArrayList<>();
		try {
			// 기존 파일 삭제
			file.delete();

			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(userFileName, true), "EUC-KR"));

			// 컬럼명 작성
			bw.write("Id,name,signUpDay,address,phoneNumber,birthday,age");
			bw.newLine();

			// 데이터 추가
			for (User data : datas) {
				if (data.getId() == id) {
					user.setDeleteUser(data);
				} else {
					temp.add(data);
					bw.write(data.getId() + "," + data.getName() + "," + data.getSignUpDay() + "," + data.getAddress()
							+ "," + data.getPhoneNumber() + "," + data.getBirthday() + "," + data.getAge());
					bw.newLine();
				}
			}
			user.setUserList(temp);
			bw.close();
		} catch (IOException e) {
			System.out.println("회원삭제에 실패하였습니다.");
		}
	}

	public static void rollbackUser(User user) {
		List<User> datas = user.getUserList();
		User deleteUser = user.getDeleteUser();
		try {
			// 기존 파일 삭제
			file.delete();

			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(userFileName, true), "EUC-KR"));

			// 컬럼명 작성
			bw.write("Id,name,signUpDay,address,phoneNumber,birthday,age");
			bw.newLine();

			// 데이터 추가
			for (User data : datas) {
				bw.write(data.getId() + "," + data.getName() + "," + data.getSignUpDay() + "," + data.getAddress() + ","
						+ data.getPhoneNumber() + "," + data.getBirthday() + "," + data.getAge());
				bw.newLine();
			}
			
			bw.write(deleteUser.getId() + "," + deleteUser.getName() + "," + deleteUser.getSignUpDay() + "," + deleteUser.getAddress() + ","
					+ deleteUser.getPhoneNumber() + "," + deleteUser.getBirthday() + "," + deleteUser.getAge());
			bw.newLine();
			user.getUserList().add(deleteUser);
			bw.close();
			user.setDeleteUser(null);
		} catch (IOException e) {
			System.out.println("회원복구에 실패하였습니다.");
		}
	}
}
