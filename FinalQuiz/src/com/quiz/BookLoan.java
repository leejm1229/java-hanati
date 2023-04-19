package com.quiz;

import java.util.Date;

public class BookLoan {
	private String bookloanId;
	private String bookId;
	private String memberId;
	private int extensionStatus; // mysql에서 tinyint로 들어가는데 int로 바꿔야 하나?
	private Date loanDate;
	private int deadline;

}
