package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	String url = "jdbc:mysql://localhost:3306/library";
	String ID = "root";
	String PW = "1234";
	Connection con;

	DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, ID, PW);
			System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("서버 연결 실패");
		}

	}

	public static void main(String[] args) {
		DBConnection dbcon = new DBConnection();
	}
}
