package com.hanati.library.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.domian.model.BookLoan;
import com.hanati.library.domian.repository.BookLoanRepository;

public class BookLoanRepositorylmpl implements BookLoanRepository {
	ArrayList<BookLoan> bookLaonList = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
	String user = "scott";
	String password = "tiger";
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<BookLoan> loadBookLoan() {
		bookLaonList.clear();
		try {
			String sql = "SELECT bookloan.*, book.title AS bookname, member.name AS membername\n" + "FROM bookloan\n"
					+ "JOIN book ON bookloan.bookid = book.id\n"
					+ "JOIN member ON bookloan.memberid = member.id ORDER BY deadline";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("bookId");
				int memberId = rs.getInt("memberId");
				String bookName = rs.getString("bookName");
				String memberName = rs.getString("memberName");
				boolean isextension = rs.getBoolean("isextension");
				Date loanDate = rs.getDate("loanDate");
				String formattedLoanDate = dateFormat.format(loanDate);
				Date deadLine = rs.getDate("deadline");
				String formatteddeadLine = dateFormat.format(deadLine);

				BookLoan bookLoan = new BookLoan(id, bookId, memberId, bookName, memberName, isextension,
						formattedLoanDate, formatteddeadLine);
				bookLaonList.add(bookLoan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return bookLaonList;
	}

	@Override
	public void save(List<BookLoan> bookLaonList) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			for (BookLoan bookLoan : bookLaonList) {
				// 중복되는 bookid가 있는지 확인
				String checkSql = "SELECT * FROM bookloan WHERE bookid = ?";

				pstmt = conn.prepareStatement(checkSql);
				pstmt.setInt(1, bookLoan.getBookId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					continue;
				}

				// 중복되는 bookid가 없으면 새로운 데이터로 저장
				String sql = "INSERT INTO BOOKLOAN(id, bookid, memberid, isextension) VALUES (BOOKLOAN_SEQ.nextval, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookLoan.getBookId());
				pstmt.setInt(2, bookLoan.getMemberId());
				pstmt.setInt(3, 0);
				pstmt.executeUpdate();

				// book 테이블에서 해당하는 bookid를 찾아 isreturned 값을 0으로 업데이트
				String updateSql = "UPDATE book SET isreturned = 0 WHERE id = ?";
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, bookLoan.getBookId());
				pstmt.executeUpdate();
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback(); // 에러 발생 시 rollback
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 리소스 해제
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void createBookLoan(BookLoan bookLoan) {
		save(bookLoan.getBookLoanList());
	}

	@Override
	public void readBookLoan(List<BookLoan> bookLoanList) {
		List<BookLoan> bookLoans = new ArrayList<>();
		bookLoans = loadBookLoan();
		if (bookLoans.size() == 0) {
			System.out.println("대출기록이 없습니다.");
		}
		for (BookLoan bookLoan : bookLoans) {
			System.out.println("ID : " + bookLoan.getBookId() + "\t" + "도서(도서번호:" + bookLoan.getBookId() + "): "
					+ bookLoan.getBookName() + "\t" + "회원(회원번호:" + bookLoan.getMemberId() + ") : "
					+ bookLoan.getMemberName() + "\t" + "연장여부 : " + bookLoan.isIsextension() + "\t" + "대출일 : "
					+ bookLoan.getLoanDate() + "\t" + "반납기한 : " + bookLoan.getDeadline());
		}

	}

	@Override
	public void updateBookLoan(int bookid) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			// bookloan 테이블에서 해당하는 bookid를 찾아서 isreturned 값을 확인
			String selectSql = "SELECT deadline, isextension FROM bookloan WHERE bookid = ?";
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, bookid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String deadline = rs.getString("deadline");
				int isextension = rs.getInt("isextension");

				// 대출연장 가능한지 체크
				if (isextension == 1) {
					System.out.println("이미 대출 연장한 도서입니다.");
					return;
				}
				LocalDate deadlineDate = LocalDate.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate newDeadline = deadlineDate.plusDays(7);
				String newDeadlineDate = newDeadline.format(DateTimeFormatter.ISO_LOCAL_DATE);

				String updateSql = "UPDATE bookloan SET deadline = ?, isextension = 1 WHERE bookid = ?";
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setString(1, newDeadlineDate);
				pstmt.setInt(2, bookid);
				pstmt.executeUpdate();

				System.out.println("대출 연장이 완료되었습니다.");
			} else {
				System.out.println("대출 정보를 찾을 수 없습니다.");
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteBookLoan(int bookid) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			// bookloan 테이블에서 해당하는 bookid의 데이터를 삭제 
			String deleteSql = "DELETE FROM bookloan WHERE bookid = ?";
			pstmt = conn.prepareStatement(deleteSql);
			pstmt.setInt(1, bookid);
			int deleteResult = pstmt.executeUpdate();

			if (deleteResult > 0) {
				// book 테이블에서 해당하는 bookid의 isreturned 값을 1로 업데이트
				String updateSql = "UPDATE book SET isreturned = 1 WHERE id = ?";
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, bookid);
				pstmt.executeUpdate();
				System.out.println("해당 도서가 반납 처리되었습니다.");
			} else {
				System.out.println("대출 정보를 찾을 수 없습니다.");
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
