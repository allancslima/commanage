package br.edu.ifal.commanage.dao;

import org.junit.Test;

import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Manager;
import br.edu.ifal.commanage.model.Salesperson;
import junit.framework.TestCase;

public class EmployeeDAOTest extends TestCase {
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
	@Test
	public void testShouldCreateEmployee() throws Exception {
		Employee employee = new Manager("Gosling", "(99)99999-9999", "gosling@email.com");

		try {
			employeeDAO.create(employee);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldReadEmployees() throws Exception {
		try {
			assertNotNull(employeeDAO.read());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldUpdateEmployee() throws Exception {
		Employee employee = new Salesperson("James Gosling", "(99)99999-9999", "gosling@email.com");
		employee.setId(1);
		
		try {
			employeeDAO.update(employee);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldDeleteEmployee() throws Exception {
		try {
			employeeDAO.delete(1);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}