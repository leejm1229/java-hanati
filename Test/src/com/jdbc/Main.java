package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:dink14";
		String user = "C##scott";
		String password = "tiger";
	
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false); 
			System.out.println("Connected to database.");

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM emp");

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				System.out.println("empno: " + empno + ", ename: " + ename);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found.");
		} catch (SQLException e) {
			System.out.println("SQL error occurred: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("Failed to close database connection: " + e.getMessage());
			}
		}
	}
}
