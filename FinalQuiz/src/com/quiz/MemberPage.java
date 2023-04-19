package com.quiz;

import java.util.Scanner;

public class MemberPage {
	Scanner scanner = new Scanner(System.in);

	MemberPage() {
		while (true) {
			System.out.println("0.뒤로 \t 1.회원조회 \t 2.회원등록 \t 3.회원수정 \t 4.회원삭제 \t 5.회원취소");
			int num = scanner.nextInt();
			if (num == 1) {
				System.out.println("회원조회");
			} else if (num == 2) {
				System.out.println("회원등록");
			} else if (num == 3) {
				System.out.println("회원수정");
			} else if (num == 4) {
				System.out.println("회원삭제");
			} else if (num == 5) {
				System.out.println("회원취소");
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

}
