package com.vaneves.querybuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/database";
		String username = "root";
		String password = "";
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (Exception e) {

		}
	}

	public static void close(Connection connection, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			close(connection);
		} catch (Exception e) {

		}
	}

	public static void close(Connection connection, Statement stmt, ResultSet result) {
		try {
			if (result != null) {
				result.close();
				result = null;
			}
			close(connection, stmt);
		} catch (Exception e) {

		}
	}
}
