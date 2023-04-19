package com.quiz;

import java.time.LocalDate;


public class BookLoan {
	private int bookId; // 도서 id
	private int memberId; // 회원 id
	private boolean extensionStatus; // 연장여부
	private LocalDate loanDate; // 대출일
	private LocalDate deadline; // 반납기한

	BookLoan(int bookId, int memberId, LocalDate loanDate, LocalDate deadline) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.extensionStatus = false;
		this.loanDate = loanDate;
		this.deadline = deadline;
	}
	
	BookLoan(int bookId, int memberId, boolean extensionStatus, LocalDate loanDate, LocalDate deadline) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.extensionStatus = extensionStatus;
		this.loanDate = loanDate;
		this.deadline = deadline;
		
		
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

	public boolean getExtensionStatus() {
		return extensionStatus;
	}

	public void setExtensionStatus(boolean extensionStatus) {
		this.extensionStatus = extensionStatus;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	
}
