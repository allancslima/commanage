package br.edu.ifal.commanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.commanage.connection.ConnectionFactory;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Manager;
import br.edu.ifal.commanage.model.Salesperson;

public class EmployeeDAO {
	
	public void create (Employee employee) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO employees (name, function, phone, email) VALUES (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getFuncion());
			stmt.setString(3, employee.getPhone());
			stmt.setString(4, employee.getEmail());
			
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
				Employee employee;
				if (rs.getString("function") == "Gerente") {
					employee = new Manager(rs.getString("name"), rs.getString("phone"), rs.getString("email"));
					employee.setId(rs.getInt("id"));
				} else {
					employee = new Salesperson(rs.getString("name"), rs.getString("phone"), rs.getString("email"));
					employee.setId(rs.getInt("id"));
				}
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
			String sql = "UPDATE employees SET name = ?, function = ?, phone = ?, email = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getFuncion());
			stmt.setString(3, employee.getPhone());
			stmt.setString(4, employee.getEmail());
			stmt.setInt(5, employee.getId());
			
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