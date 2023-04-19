package com.quiz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class MemberPage {
	Scanner scanner = new Scanner(System.in);
	Member member;
	MemberConvert convert = new MemberConvert();

	MemberPage() {
		while (true) {

			System.out.println("0.뒤로 \t 1.회원조회 \t 2.회원등록 \t 3.회원수정 \t 4.회원삭제 \t 5.회원복구");
			int num = scanner.nextInt();
			if (num == 1) {
				showMemberInfo();
			} else if (num == 2) {
				registerMember();
			} else if (num == 3) {
				updateMember();
			} else if (num == 4) {
				deleteMember();
			} else if (num == 5) {
				rollback();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	// 회원 조회
	public void showMemberInfo() {
		if (Util.isState("member.csv")) {
			List<Member> members = convert.showInfoMember("member.csv");

			for (Member member : members) {
				System.out.println("id : " + member.getMemberId() + "\t" + "이름 : " + member.getName() + "\t" + "가입날짜 : "
						+ member.getSignUpDay() + "\t" + "주소 : " + member.getAddress() + "\t" + "전화번호 : "
						+ member.getPhoneNumber() + "\t" + "생일 : " + member.getBirthday()+ "\t\t" + "나이 : " + member.getAge());
			}
		} else {
			System.out.println("등록된 멤버가 없습니다.");
		}

	}

	// 회원 등록
	public void registerMember() {
		System.out.println("멤버 id를 입력하세요.");
		int memberId = scanner.nextInt();

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
		convert.registerMember(member, "member.csv");
	}

	// 회원 수정
	public void updateMember() {
		System.out.println("수정할 회원의 id를 입력하세요.");
		int memberId = scanner.nextInt();
		convert.updateMember(memberId, "member.csv");
	}

	// 회원 삭제
	public void deleteMember() {
		System.out.println("삭제할 회원의 id를 입력하세요.");
		int memberId = scanner.nextInt();
		convert.deleteMember(memberId, "member.csv");
	}

	// 삭제 취소
	public void rollback() {
		convert.rollback();
	}

}
