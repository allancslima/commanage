package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Product;

public class ProductDAO {
	
	public void create(Product product) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO products (name, category, purchasePrice, salePrice) VALUES (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPurchasePrice());
			stmt.setDouble(4, product.getSalePrice());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Product> read() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM products";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Product product = new Product(rs.getString("name"),
											  rs.getString("category"),
											  rs.getDouble("purchasePrice"),
											  rs.getDouble("salePrice"));
				product.setId(rs.getInt("id"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return products;
	}
	
	public void update(Product product) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt= null;
		
		try {
			String sql = "UPDATE products SET name = ?, category = ?, purchasePrice = ?, salePrice = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPurchasePrice());
			stmt.setDouble(4, product.getSalePrice());
			stmt.setInt(5, product.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while updating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(int productId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM products WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public Product findById(int productId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = new Product("", "", 0, 0);
		
		try {
			String sql = "SELECT * FROM products WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productId);
			rs = stmt.executeQuery();
			
			rs.beforeFirst();
			while (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPurchasePrice(rs.getDouble("purchasePrice"));
				product.setSalePrice(rs.getDouble("salePrice"));
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading product");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return product;
	}
}