package com.hanati.library.presentation;

import java.util.Scanner;

public class MainController {
	Scanner scanner = new Scanner(System.in);
	MemberController memberController;
	BookController bookController;
	BookLoanController bookLoanController;

	public MainController() {
		while (true) {
			System.out.println("0.종료 \t 1.회원관리 \t 2.도서관리 \t 3.도서대출");
			int num = scanner.nextInt();
			if (num == 1) {
				memberController = new MemberController();
			} else if (num == 2) {
				bookController= new BookController();
			} else if (num == 3) {
				bookLoanController = new BookLoanController();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}

	}
}
