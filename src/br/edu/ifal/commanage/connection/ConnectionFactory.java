package br.edu.ifal.commanage.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost:3306/commanage?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static Connection getConnection() throws SQLException {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (SQLException e) {
			throw new SQLException("Error trying connect to database");
		}
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			throw new SQLException("Connection close error");
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException {
		closeConnection(con);
		
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			throw new SQLException("Connection close error");
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException {
		closeConnection(con, stmt);
		
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new SQLException("Connection close error");
		}
	}
}