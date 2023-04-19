package com.hanati.library.domian.repository;

import java.util.List;

import com.hanati.library.domian.model.BookLoan;

public interface BookLoanRepository {

	List<BookLoan> loadBookLoan();

	void save(List<BookLoan> bookLoanList);

	void createBookLoan(BookLoan bookLoan);

	void readBookLoan(List<BookLoan> bookLoanList);

	void updateBookLoan(int bookid);

	void deleteBookLoan(int bookid);
}

