package com.hanati.library.domian.model;

import java.util.List;

public class Book {
	private List<Book> bookList; // 모든 도서
	private int id; // 도서 id
	private String title; // 제목
	private String publishDate; // 출간일
	private boolean isReturned; // 반납여부

	
	public Book(String title, String publishDate) {
		this.title = title;
		this.publishDate = publishDate;
	}
	
	public Book(int id, String title, String publishDate, boolean isReturned) {
		this.id = id;
		this.title = title;
		this.publishDate = publishDate;
		this.isReturned = isReturned;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public void showInfo() {

	}
}