package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Employee;

public class EmployeeDAO {
	
	public void create (Employee employee) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO employees (name, phone, email) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getPhone());
			stmt.setString(3, employee.getEmail());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while creating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Employee> read () throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM employees";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Employee employee = new Employee(rs.getString("name"),
												 rs.getString("phone"),
												 rs.getString("email"));
				employee.setId(rs.getInt("id"));
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new SQLException("Error while reading");
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return employees;
	}
	
	public void update (Employee employee) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE employees SET name = ?, phone = ?, email = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getPhone());
			stmt.setString(3, employee.getEmail());
			stmt.setInt(4, employee.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while updating");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete (int employeeId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE FROM employees WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, employeeId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}