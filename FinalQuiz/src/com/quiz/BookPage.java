package com.quiz;

import java.util.Scanner;

public class BookPage {
	Scanner scanner = new Scanner(System.in);

	BookPage() {
		while (true) {
			System.out.println("0.뒤로 \t 1.도서등록 \t 2.도서삭제 \t 3.도서조회(대출가능) \t 4.도서조회(최근 출간)");
			int num = scanner.nextInt();
			if (num == 1) {
				System.out.println("도서등록");
			} else if (num == 2) {
				System.out.println("도서삭제");
			} else if (num == 3) {
				System.out.println("도서조회(대출가능)");
			} else if (num == 4) {
				System.out.println("도서조회(최근 출간)");
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}
}
