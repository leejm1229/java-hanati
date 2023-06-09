package com.hanati.library.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.hanati.library.domian.model.Book;
import com.hanati.library.domian.model.Member;
import com.hanati.library.domian.repository.BookRepository;

public class BookRepositorylmpl implements BookRepository {

	ArrayList<Book> bookList = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtInsert = null;
	ResultSet rs = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
	String user = "scott";
	String password = "tiger";
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Book> loadBook() {
		bookList.clear();
		try {
			String sql = "SELECT * FROM book WHERE isreturned = 1";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String publishDate = rs.getString("publishdate");
				boolean isreturned = rs.getBoolean("isreturned");
				Book book = new Book(id, title, publishDate, isreturned);
				bookList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
	}

	@Override
	public void save(List<Book> bookList) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			for (Book book : bookList) {
				// 중복되는 PhoneNumber가 있는지 확인
				String checkSql = "SELECT * FROM book WHERE title = ?";
				pstmt = conn.prepareStatement(checkSql);
				pstmt.setString(1, book.getTitle());
				rs = pstmt.executeQuery();
				if (rs.next()) { // 중복되는 TITLE가 있으면 continue
					continue;
				}

				// 중복되는 PhoneNumber가 없으면 새로운 데이터로 저장
				String sql = "INSERT INTO BOOK(id, title, publishdate, isreturned) VALUES (BOOK_SEQ.nextval, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book.getTitle());
				pstmt.setString(2, book.getPublishDate());
				pstmt.setInt(3, 1);
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
	public void createBook(Book book) {
		save(book.getBookList());
	}

	@Override
	public void readBook() {
		List<Book> books = new ArrayList<>();
		books = loadBook();
		for (Book book : books) {
			System.out.println("id : " + book.getId() + "\t" + "제목 : " + book.getTitle() + "\t" + "출간일 : "
					+ book.getPublishDate().substring(0, 4) + "-" + book.getPublishDate().substring(4, 6) + "-"
					+ book.getPublishDate().substring(6, 8) + "\t" + "대출가능(반납여부) : " + book.getIsReturned());
		}
	}

	@Override
	public void updateBook(int id) {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "UPDATE BOOK SET title=?, publishdate=? WHERE id=?";

			System.out.println("도서이름을 입력하세요.");
			String title = scanner.next();

			scanner.nextLine();
			System.out.println("출간날짜를 입력하세요.(YYYYMMDD)");
			String publishdate = scanner.nextLine();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, publishdate);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();

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
	public void deleteBook(int id) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			String delSql = "DELETE FROM book WHERE id = ?";
			pstmt = conn.prepareStatement(delSql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();

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
