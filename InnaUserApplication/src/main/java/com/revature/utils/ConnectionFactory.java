package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		
//		String url = "jdbc:oracle:thin:@localhost:1521:firstDB";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "inna";
		String password = "123";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("success connection!");
		} catch (SQLException e) {
			System.out.println("Unable to obtain connection to database" + "/n" + e);
		}
		return conn;
	}
}