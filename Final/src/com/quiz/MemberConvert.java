package com.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberConvert {
	Scanner scanner = new Scanner(System.in);
	Member member;

	public void registerMember(Member member, String fileName) {
		try {
			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fileName, true), "EUC-KR"));

			// 파일이 존재하지 않는 경우에만 컬럼명 작성
			if (new File(fileName).length() == 0) {
				bw.write("memberId,name,signUpDay,address,phoneNumber,birthday,age");
				bw.newLine();
			}

			// 데이터 추가
			bw.write(member.getMemberId() + "," + member.getName() + "," + member.getSignUpDay() + ","
					+ member.getAddress() + "," + member.getPhoneNumber() + "," + member.getBirthday() + ","
					+ member.getAge());
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			System.out.println("회원등록에 실패하였습니다.");
		}
	}

	public ArrayList<Member> showInfoMember(String fileName) {
		ArrayList<Member> memberList = new ArrayList<>();
		
		try {
			// 파일 열고
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));

			// 첫 줄은 컬럼명 빼고
			String lines = br.readLine();

			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int memberId = Integer.parseInt(line[0]);
				String name = line[1];
				LocalDate signUpDay = LocalDate.parse(line[2]);
				String address = line[3];
				String phoneNumber = line[4];
				LocalDate birthday = LocalDate.parse(line[5]);
				int age = Integer.parseInt(line[6]);
				Member member = new Member(memberId, name, signUpDay, address, phoneNumber, birthday, age);
				memberList.add(member);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("등록된 멤버가 없습니다.");
		}

		return memberList;
	}

	public void updateMember(int memberId, String fileName) {
		deleteMember(memberId, fileName);

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

		member = new Member(memberId, name, signUpDay, address, phoneNumber, birthday, age);
		registerMember(member, "member.csv");

	}

	public void deleteMember(int memberId, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.csv"), "EUC-KR"));
			BufferedWriter trash = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("trash.csv"), "EUC-KR"));

			String lines;
			int lineNum = 0; // 컬럼명 추가를 위해
			while ((lines = br.readLine()) != null) {
				if (lineNum == 0) { // 첫 번째 라인인 경우
					bw.write(lines); // 기존 컬럼명 추가
					bw.newLine();
				} else {
					String[] line = lines.split(",");
					int id = Integer.parseInt(line[0]);

					if (id == memberId) { // 일치하는 id면 이 부분 빼고
						trash.write(lines);
						continue;
					}

					bw.write(lines);
					bw.newLine();
				}
				lineNum++;
			}

			br.close();
			bw.close();
			trash.close();

			File file = new File(fileName);
			file.delete();

			File tempFile = new File("temp.csv");
			tempFile.renameTo(file);

		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void rollback() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("trash.csv"), "EUC-KR"));
			// 파일 생성
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("member.csv", true), "EUC-KR"));

			// 파일이 존재하지 않는 경우에만 컬럼명 작성
			if (new File("member.csv").length() == 0) {
				bw.write("memberId,name,signUpDay,address,phoneNumber,birthday,age");
				bw.newLine();
			}

			String lines;
			// 데이터 읽기
			while ((lines = br.readLine()) != null) {
				String[] line = lines.split(",");
				int memberId = Integer.parseInt(line[0]);
				String name = line[1];
				LocalDate signUpDay = LocalDate.parse(line[2]);
				String address = line[3];
				String phoneNumber = line[4];
				LocalDate birthday = LocalDate.parse(line[5]);
				int age = Integer.parseInt(line[6]);
				Member member = new Member(memberId, name, signUpDay, address, phoneNumber, birthday, age);

				// 데이터 추가
				bw.write(member.getMemberId() + "," + member.getName() + "," + member.getSignUpDay() + ","
						+ member.getAddress() + "," + member.getPhoneNumber() + "," + member.getBirthday()+ "," + member.getAge());
				bw.newLine();
			}

			br.close();
			bw.close();

			File file = new File("trash.csv");
			file.delete();
		} catch (IOException e) {
			System.out.println("회원복구에 실패했습니다.");
		}
	}
}
