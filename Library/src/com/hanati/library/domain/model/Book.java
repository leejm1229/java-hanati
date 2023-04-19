package com.hanati.library.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Book {
	private List<Book> bookList; // 모든 도서 
	private int id;  // 도서 id
	private String title;	// 제목 
	private LocalDate publishDate;  // 출간일 
	private boolean isReturned=true; // 반납여부 
	
	
	public Book() {
		// 모든 책을 조회해서 리스트에 담기 
	}

	public Book(int id, String title, LocalDate publishDate, boolean isReturned){
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
	
	int getId() {
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

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public boolean getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	@Override
	public String toString() {
		return "Id=" + id + ", 제목 = " + title + ", 출간일 = " + publishDate
				+ ", 반환여부 = " + isReturned;
	}
	
	

	
}
