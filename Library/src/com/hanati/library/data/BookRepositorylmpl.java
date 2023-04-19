package com.hanati.library.data;

import java.util.List;

import com.hanati.library.domain.model.Book;
import com.hanati.library.domain.repository.BookRepository;

public class BookRepositorylmpl implements BookRepository{

	@Override
	public List<Book> findAll(Book book) {
		return book.getBookList();
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Book book, int id) {
		// TODO Auto-generated method stub
		
	}

}
