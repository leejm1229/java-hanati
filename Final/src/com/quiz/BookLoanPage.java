package com.quiz;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookLoanPage {
	Scanner scanner = new Scanner(System.in);
	BookLoan bookLoan;
	BookLanConvert convert = new BookLanConvert();

	BookLoanPage() {
		while (true) {
			System.out.println("0.뒤로 \t 1.대출조회 \t 2.도서대출 \t 3.대출연장 \t 4.도서반납");
			int num = scanner.nextInt();
			if (num == 1) {
				showInfoBookLoan();
			} else if (num == 2) {
				bookLoanRegister();
			} else if (num == 3) {
				bookExtension();
			} else if (num == 4) {
				bookReturn();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	// 대출 조회
	public void showInfoBookLoan() {
		if (Util.isState("bookLoan.csv")) {
			List<BookLoan> bookLoans = convert.showInfoBookLoan("bookLoan.csv");

			// 반납기한이 얼마 안남은 순으로 정렬
			Collections.sort(bookLoans, new Comparator<BookLoan>() {
				@Override
				public int compare(BookLoan bl1, BookLoan bl2) {
					return bl1.getDeadline().compareTo(bl2.getDeadline());
				}
			});

			for (BookLoan bookLoan : bookLoans) {
				System.out.println("도서 id : " + bookLoan.getBookId() + "\t" + "회원 id : " + bookLoan.getMemberId() + "\t"
						+ "연장여부 : " + bookLoan.getExtensionStatus() + "\t" + "대출일 : " + bookLoan.getLoanDate() + "\t"
						+ "반납기한 : " + bookLoan.getDeadline());
			}
		} else {
			System.out.println("등록된 도서대출 정보가 없습니다.");
		}
	}

	// 도서 대출
	public void bookLoanRegister() {
		System.out.println("도서 id를 입력하세요.");
		int bookId = scanner.nextInt();

		System.out.println("회원 id를 입력하세요.");
		int memberId = scanner.nextInt();

		// 현재 날짜 받기
		LocalDate loanDate = LocalDate.now();

		// 반납기한 -> 현재날짜 + 14
		LocalDate deadline = loanDate.plusDays(14);

		bookLoan = new BookLoan(bookId, memberId, loanDate, deadline);
		convert.bookLoanRegister(bookLoan, bookId, memberId, "bookLoan.csv");
	}

	// 대출 연장
	public void bookExtension() {
		System.out.println("연장하고 싶은 도서 id를 입력하세요.");
		int bookId = scanner.nextInt();
		convert.bookExtension(bookId, "bookLoan.csv");
	}

	// 도서반납
	public void bookReturn() {
		System.out.println("반납할 도서 id를 입력하세요.");
		int bookId = scanner.nextInt();
		convert.bookReturn(bookId);

	}
}
