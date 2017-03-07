package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Sale;

public class SaleDAO {
	
	public void create (Sale sale) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO sales (productId, quantity) VALUES (?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, sale.getProductId());
			stmt.setInt(2, sale.getQuantity());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Sale> read () throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Sale> sales = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM sales";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Sale sale = new Sale(rs.getInt("productId"),
									 rs.getInt("quantity"));
				sale.setId(rs.getInt("id"));
				
				sales.add(sale);
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return sales;
	}
	
	public void update (Sale sale) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt= null;
		
		try {
			String sql = "UPDATE sales SET productId = ?, quantity = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, sale.getProductId());
			stmt.setInt(2, sale.getQuantity());
			stmt.setInt(3, sale.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while updating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete (int saleId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM sales WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, saleId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}