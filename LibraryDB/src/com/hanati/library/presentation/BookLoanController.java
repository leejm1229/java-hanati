package com.hanati.library.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.data.BookLoanRepositorylmpl;
import com.hanati.library.domian.model.BookLoan;

public class BookLoanController {
	Scanner scanner = new Scanner(System.in);
	List<BookLoan> bookLoanList = new ArrayList<>();
	BookLoanRepositorylmpl bookLoanRepository = new BookLoanRepositorylmpl();
	BookLoanController() {
		
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
		bookLoanRepository.readBookLoan(bookLoanList);
	}

	// 도서 대출
	public void bookLoanRegister() {
		System.out.println("도서번호를 입력하세요.");
		int bookid = scanner.nextInt();

		System.out.println("회원번호를 입력하세요.");
		int memberid = scanner.nextInt();
		
		BookLoan bookLoan = new BookLoan(bookid, memberid);
		
		bookLoanList.add(bookLoan);
		bookLoan.setBookLoanList(bookLoanList);		
		bookLoanRepository.createBookLoan(bookLoan);
	}

	// 대출 연장
	public void bookExtension() {
		System.out.println("연장하고싶은 도서 ID를 입력하세요.");
		int bookid = scanner.nextInt();
		bookLoanRepository.updateBookLoan(bookid);
	}

	// 도서 반납
	public void bookReturn() {
		System.out.println("반납하려는 도서 ID를 입력하세요.");
		int bookid = scanner.nextInt();
		bookLoanRepository.deleteBookLoan(bookid);
	}
}
