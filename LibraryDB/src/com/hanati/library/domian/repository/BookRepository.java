package com.hanati.library.domian.repository;

import java.util.List;

import com.hanati.library.domian.model.Book;

public interface BookRepository {

	List<Book> loadBook();

	void save(List<Book> bookList);

	void createBook(Book book);

	void readBook();

	void updateBook(int id);

	void deleteBook(int id);


}
