package com.hanati.library.domain.repository;

import java.util.List;

import com.hanati.library.domain.model.Book;

public interface BookRepository {

	List<Book> findAll(Book book);
	
	void addBook(Book book);
	
	void deleteBook(Book book, int id);
	
	
	
}
