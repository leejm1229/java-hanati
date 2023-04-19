package com.quiz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookPage {
	Scanner scanner = new Scanner(System.in);
	Book book;
	BookConvert convert = new BookConvert();

	BookPage() {
		while (true) {
			System.out.println("0.뒤로 \t 1.도서등록 \t 2.도서삭제 \t 3.도서조회");
			int num = scanner.nextInt();
			if (num == 1) {
				registerBook();
			} else if (num == 2) {
				deleteBook();
			} else if (num == 3) {
				showInfoBook();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	// 도서 등록
	public void registerBook() {
		System.out.println("도서 id를 입력하세요.");
		int bookId = scanner.nextInt();

		scanner.nextLine();
		System.out.println("도서 제목을 입력하세요.");
		String title = scanner.nextLine();

		System.out.println("도서 출간일을 입력하세요.(yyyy/MM/dd)");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String input = scanner.next();
		LocalDate publishDate = LocalDate.parse(input, formatter);

		book = new Book(bookId, title, publishDate);
		convert.registerBook(book, "book.csv");
	}

	// 도서 삭제
	public void deleteBook() {
		System.out.println("삭제할 도서의 id를 입력하세요.");
		int bookId = scanner.nextInt();
		convert.deleteBook(bookId, "book.csv");

	}

	// 도서 조회
	public void showInfoBook() {
		if(Util.isState("book.csv")) {
			List<Book> books = convert.showInfoBook("book.csv");
			
			
			
			// 최신 출간일순으로 정렬
		    Collections.sort(books, new Comparator<Book>() {
		        @Override
		        public int compare(Book b1, Book b2) {
		            return b2.getPublishDate().compareTo(b1.getPublishDate());
		        }
		    });
			
			for (Book book : books) {
				System.out.println("id : " + book.getBookId() + "\t" + "제목 : " + book.getTitle() + "\t" + "출간일 : "
						+ book.getPublishDate() + "\t" + "대출가능(반납여부) : " + book.getIsReturned());
			}
		}else {
			System.out.println("등록된 도서가 없습니다.");
		}
		
	}
}
