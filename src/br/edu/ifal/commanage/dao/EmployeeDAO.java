package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Employee;

public class EmployeeDAO {
	
	public void create (Employee employee) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO employees (name, phone, email) VALUES (?, ?, ?)");
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
	
	public ArrayList<Employee> read () throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Employee> employees = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM employees");
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
			stmt = con.prepareStatement("UPDATE employees SET name = ?, phone = ?, email = ? WHERE id = ?");
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
			stmt = con.prepareStatement("DELETE FROM employees WHERE id = ?");
			stmt.setInt(1, employeeId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error while deleting");
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}