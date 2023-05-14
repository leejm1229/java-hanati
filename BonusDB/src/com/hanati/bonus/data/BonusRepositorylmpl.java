package com.hanati.bonus.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.hanati.bonus.domain.model.Emp;
import com.hanati.bonus.domain.repository.BonusRepository;

public class BonusRepositorylmpl implements BonusRepository {
	Connection conn = null;
	Statement stmt = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:dink14";
	String user = "C##scott";
	String password = "tiger";

	@Override
	public void saveBonus(List<Emp> empList) {
		// 넘겨준 empList에서임시 변수에 값을 넣은 후 에 저장해준다.
		for (Emp emp : empList) {
			String ename = emp.getEname();
			String job = emp.getJob();
			int sal = emp.getSal();
			int comm = emp.getComm();

			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				
				// 넘어온 empList를 이용하여 bonus 테이블에 데이터 삽입 
				stmt = conn.createStatement();
				String sql = "INSERT INTO bonus (ename, job, sal, comm) VALUES ('" + ename + "', '" + job + "', "
						+ sal + ", " + comm + ")";
				stmt.executeUpdate(sql);

				conn.commit(); // Insert 하나하나 할 때마다 커밋 
				System.out.println("Bonus inserted for " + ename);
 
			} catch (ClassNotFoundException e) {
				System.out.println("JDBC driver not found.");
			} catch (SQLException e) {
				System.out.println("SQL error occurred: " + e.getMessage());
				try {
					if (conn != null) {
						conn.rollback();
						System.out.println("Transaction rolled back");
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
