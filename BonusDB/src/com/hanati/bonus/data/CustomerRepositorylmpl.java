package com.hanati.bonus.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hanati.bonus.domain.model.Customer;
import com.hanati.bonus.domain.repository.CustomerRepository;

public class CustomerRepositorylmpl implements CustomerRepository {
	Map<Integer, Integer> map = new HashMap<>();
	private List<Customer> customerList = new ArrayList<>();
	Connection conn = null;
	Statement stmt = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:dink14";
	String user = "C##scott";
	String password = "tiger";

	@Override
	public List<Customer> loadCustomer() {
		customerList.clear();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			System.out.println("Connected to database.");

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
			
			// 고객의 데이터를 읽어오고 해쉬맵을 이용하여 해당하는 empno가 있으면 1을 더해주고 없다면 1로 초기화 해준다.
			while (rs.next()) {
				int mgr_empno = rs.getInt("mgr_empno");
				
				if (map.containsKey(mgr_empno)) {
					int num = map.get(mgr_empno)+1;
					map.put(mgr_empno, num);
				} else {
					map.put(mgr_empno, 1);
				}
			}
			
			// 해쉬맵을 리스트로 저장한 후 리스트를 이용하여 다른 곳에서 사용한다.
			for(Integer key : map.keySet()) {
				Customer customer = new Customer(key, map.get(key));
				customerList.add(customer);
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
		return customerList;
	}
}
