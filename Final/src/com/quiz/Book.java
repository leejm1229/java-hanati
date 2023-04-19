package com.quiz;

import java.time.LocalDate;

public class Book {
	private int bookId;  // 도서 id
	private String title;	// 제목 
	private LocalDate publishDate;  // 출간일 
	private boolean isReturned; // 반납여부 
	
	
	Book(int bookId, String title, LocalDate publishDate){
		this.bookId = bookId;
		this.title = title;
		this.publishDate = publishDate;
		this.isReturned = true;
	}

	Book(int bookId, String title, LocalDate publishDate, boolean isReturned){
		this.bookId = bookId;
		this.title = title;
		this.publishDate = publishDate;
		this.isReturned = isReturned;
	}
	
	int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

}
