package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Purchase;

public class PurchaseDAO {
	
	public void create (Purchase purchase) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO purchases (productId, quantity) VALUES (?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, purchase.getProductId());
			stmt.setInt(2, purchase.getQuantity());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Purchase> read () throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Purchase> purchases = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM purchases";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Purchase purchase = new Purchase(rs.getInt("productId"),
											  rs.getInt("quantity"));
				purchase.setId(rs.getInt("id"));
				
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return purchases;
	}
	
	public void delete (int purchaseId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM purchases WHERE id = ?";
			stmt = con.prepareStatement(sql);	
			stmt.setInt(1, purchaseId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}