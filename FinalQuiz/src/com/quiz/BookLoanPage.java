package com.quiz;

import java.util.Scanner;

public class BookLoanPage {
	Scanner scanner = new Scanner(System.in);

	BookLoanPage() {
		while (true) {
			System.out.println("0.뒤로 \t 1.대출조회 \t 2.도서대출 \t 3.대출연장");
			int num = scanner.nextInt();
			if (num == 1) {
				System.out.println("대출조회");
			} else if (num == 2) {
				System.out.println("도서대출");
			} else if (num == 3) {
				System.out.println("대출연장");
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}
}
