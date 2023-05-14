package com.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;


public class Main {

    final static String DB_URL =
            "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=/Users/lee/Documents/Cloud/Wallet_DinkDB";
    final static String DB_USER = "DA2314";
    final static String DB_PASSWORD = "Data2314";


    public static void main(String args[]) throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");


        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println(
                    "Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
            System.out.println("Database Username is: " + connection.getUserName());
            System.out.println();
            printEmployees(connection);
        }
    }

    public static void printEmployees(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet =
                    statement.executeQuery("select first_name, last_name from employees")) {
                System.out.println("FIRST_NAME" + "  " + "LAST_NAME");
                System.out.println("---------------------");
                while (resultSet.next())
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " ");
            }
        }
    }
}
