package com.hanati.library.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.data.BookRepositorylmpl;
import com.hanati.library.domian.model.Book;
import com.hanati.library.domian.model.Member;
import com.hanati.library.domian.repository.BookRepository;

public class BookController {
	Scanner scanner = new Scanner(System.in);
	List<Book> bookList = new ArrayList<>();
	private BookRepository bookRepository = new BookRepositorylmpl();

	BookController() {
		while (true) {
			System.out.println("0.뒤로 \t 1.도서조회 \t 2.도서등록 \t 3.도서수정 \t 4.도서삭제");
			int num = scanner.nextInt();
			if (num == 1) {
				showInfoBook();
			} else if (num == 2) {
				createBook();
			} else if (num == 3) {
				updateBook();
			} else if (num == 4) {
				deleteBook();
			} else if (num == 0) {
				break;
			} else {
				System.out.println("메뉴버튼을 다시 클릭해주세요.");
			}
		}
	}

	// 도서 조회
	public void showInfoBook() {
		bookRepository.readBook();
	}

	// 도서 대출
	public void createBook() {
		System.out.println("도서이름을 입력하세요.");
		String title = scanner.next();

		scanner.nextLine();
		System.out.println("출간날짜를 입력하세요.(YYYYMMDD)");
		String publishdate = scanner.nextLine();


		Book book = new Book(title, publishdate);
		
		bookList.add(book);
		book.setBookList(bookList);
		bookRepository.createBook(book);
	}

	// 도서 수정
	public void updateBook() {
		System.out.println("수정하고싶은 도서 ID를 입력하세요.");
		int id = scanner.nextInt();
		bookRepository.updateBook(id);
	}

	// 도서 삭제
	public void deleteBook() {
		System.out.println("삭제하고싶은 도서 ID를 입력하세요.");
		int id = scanner.nextInt();
		bookRepository.deleteBook(id);
	}

}
