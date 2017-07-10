package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifal.commanage.connection.ConnectionFactory;

public class StockDAO {
	
	public void create(int productId, int quantity) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO stock (productId, quantity) VALUES (?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			stmt.setInt(2, quantity);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public Map<Integer, Integer> read() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<Integer, Integer> mapProducts = new HashMap<>();
		
		try {
			String sql = "SELECT * FROM stock";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next())
				mapProducts.put(rs.getInt("productId"), rs.getInt("quantity"));
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return mapProducts;
	}
	
	public void update(int productId, int quantity) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE stock SET quantity = ? WHERE productId = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, quantity);
			stmt.setInt(2, productId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while updating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(int productId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM stock WHERE productId = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}