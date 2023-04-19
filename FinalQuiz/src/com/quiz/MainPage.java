package com.quiz;

import java.util.Scanner;

public class MainPage {
	Scanner scanner = new Scanner(System.in);
	MemberPage memberPage;
	BookPage bookPage;
	BookLoanPage bookLoanPage;
	MainPage(){
		while(true) {
			System.out.println("0.종료 \t 1.회원관리 \t 2.도서관리 \t 3.도서대출");
			int num = scanner.nextInt();
			if(num == 1) {
				memberPage = new MemberPage();
			}else if(num==2) {
				bookPage = new BookPage();
			}else if(num==3) {
				bookLoanPage = new BookLoanPage();
			}else if(num==0) {
				break;
			}else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
		
	}

}
