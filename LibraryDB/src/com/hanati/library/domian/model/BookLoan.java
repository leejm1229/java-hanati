package com.hanati.library.domian.model;

import java.util.List;

public class BookLoan {
	private List<BookLoan> bookLoanList;
	private int bookLoanId; // 대출 ID
	private int bookId; // 도서 id
	private int memberId; // 회원 id
	private String bookName; // 도서 이름
	private String memberName; // 회원 이름
	private boolean isextension; // 연장여부
	private String loanDate; // 대출일
	private String deadline; // 반납기한
	
	public BookLoan(int bookId, int memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
	}
	
	public BookLoan(int bookLoanId, int bookId, int memberId, String bookName, String memberName, boolean isextension,
			String loanDate, String deadline) {
		this.bookLoanId = bookLoanId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.bookName = bookName;
		this.memberName = memberName;
		this.isextension = isextension;
		this.loanDate = loanDate;
		this.deadline = deadline;
	}

	public List<BookLoan> getBookLoanList() {
		return bookLoanList;
	}

	public void setBookLoanList(List<BookLoan> bookLoanList) {
		this.bookLoanList = bookLoanList;
	}

	public int getBookLoanId() {
		return bookLoanId;
	}

	public void setBookLoanId(int bookLoanId) {
		this.bookLoanId = bookLoanId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public boolean isIsextension() {
		return isextension;
	}

	public void setIsextension(boolean isextension) {
		this.isextension = isextension;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

}
