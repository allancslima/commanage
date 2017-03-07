package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Provider;

public class ProviderDAO {
	
	public void create (Provider provider) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO providers (name, phone, email) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, provider.getName());
			stmt.setString(2, provider.getPhone());
			stmt.setString(3, provider.getEmail());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Provider> read () throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Provider> providers = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM providers";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Provider provider = new Provider(rs.getString("name"),
											  rs.getString("phone"),
											  rs.getString("email"));
				provider.setId(rs.getInt("id"));
				
				providers.add(provider);
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return providers;
	}
	
	public void update (Provider provider) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt= null;
		
		try {
			String sql = "UPDATE providers SET name = ?, phone = ?, email = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, provider.getName());
			stmt.setString(2, provider.getPhone());
			stmt.setString(3, provider.getEmail());
			stmt.setInt(5, provider.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while updating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete (int productId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM providers WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, productId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException ("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}