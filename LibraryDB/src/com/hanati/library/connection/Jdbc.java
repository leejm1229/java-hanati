package com.hanati.library.connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.119.119:1521:dink";
        String user = "scott";
        String password = "tiger";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database.");

            // 파일에서 SQL 스크립트 읽기
            String sql = new String(Files.readAllBytes(Paths.get("./library.sql")));

            // SQL 스크립트 실행
            stmt = conn.createStatement();
            System.out.println("성공");
            int count = stmt.executeUpdate(sql);
            System.out.println("성공");
            System.out.println("Updated " + count + " rows.");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found.");
        } catch (SQLException e) {
            System.out.println("SQL error occurred: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read SQL script: " + e.getMessage());
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
