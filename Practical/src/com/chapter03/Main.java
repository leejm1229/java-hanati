package com.chapter03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		List<Book> bookList = new ArrayList<>();
		Set<Book> bookSet = new HashSet<>();

		Date publishDate1 = new Date(1234567890000L);
		Date publishDate2 = new Date(1316622225935L);

		Book book1 = new Book("백두산", publishDate1);
		Book book2 = new Book("백두산2", publishDate1);
		Book book3 = new Book("백두산3", publishDate2);
		Book book4 = new Book("백두산", publishDate1);

		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);

		bookSet.add(book1);
		bookSet.add(book2);
		bookSet.add(book3);
		bookSet.add(book4);

		List<Book> books2 = new ArrayList<>();

		// 1
		System.out.println(bookSet.size()); // 3
		System.out.println(book1.equals(book4)); // book1과 book4는 같다.

		System.out.println();

		// 2
		Collections.sort(bookList);

		for (Book book : bookList) {
			System.out.println(book.getTitle() + " : " + book.getPublishDate());
		}

		System.out.println();

		// 3
		List<Book> bookList2 = new ArrayList<>();

		for (Book book : bookList) {
			bookList2.add(book.clone());
		}

		for (Book book : bookList2) {
			System.out.println(book.getTitle() + " : " + book.getPublishDate());
		}

	}
}
