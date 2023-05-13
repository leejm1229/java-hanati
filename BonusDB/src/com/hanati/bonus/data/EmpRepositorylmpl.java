package com.hanati.bonus.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hanati.bonus.domain.model.Customer;
import com.hanati.bonus.domain.model.Emp;
import com.hanati.bonus.domain.repository.EmpRepository;

public class EmpRepositorylmpl implements EmpRepository {
	List<Emp> empList = new ArrayList<>();

	Connection conn = null;
	Statement stmt = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:dink14";
	String user = "C##scott";
	String password = "tiger";

	@Override
	public List<Emp> calCulatorEmp(List<Customer> customerList) {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			System.out.println("Connected to database.");
			
			// empno에 해당하는 컬럼을 가져온다.
			for (Customer customer : customerList) {
				int empno = customer.getMgr_empno();
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM EMP WHERE EMPNO =" + empno);
				
				while (rs.next()) {
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int sal = rs.getInt("sal");
					int comm = rs.getInt("comm");
					// 애널리스트(Analyst)에게는 보너스 미지급
					if (empno == 7788) {
						Emp emp = new Emp(empno, ename, job, sal, comm, customer.getCount());
						empList.add(emp);
					}else {
						if (customer.getCount() >= 100000) {
							Emp emp = new Emp(empno, ename, job ,sal, comm + 2000, customer.getCount());
							empList.add(emp);
						} else {
							Emp emp = new Emp(empno, ename, job, sal, comm + 1000, customer.getCount());
							empList.add(emp);
						}
					}
					
				}
				rs.close();
			}
			// 대통령은 따로 관리하는 고객이 없기 때문에 수작업으로 저장한다.
			stmt = conn.createStatement();
			int empno = 7839;
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMP WHERE EMPNO =" + empno);
			while (rs.next()) {
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				Emp emp = new Emp(empno, ename, job, sal, comm, 0);
				empList.add(emp);
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
		return empList;
	}

}
