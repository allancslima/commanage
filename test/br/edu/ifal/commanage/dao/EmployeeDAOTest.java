package br.edu.ifal.commanage.dao;

import org.junit.Test;
import junit.framework.TestCase;
import br.edu.ifal.commanage.model.Employee;

public class EmployeeDAOTest extends TestCase {
	
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
	@Test
	public void testShouldCreateEmployee () throws Exception {
		Employee employee = new Employee("Gosling", "(99)99999-9999", "gosling@email.com");

		try {
			employeeDAO.create(employee);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldReadEmployees () throws Exception {
		try {
			assertNotNull(employeeDAO.read());
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldUpdateEmployee () throws Exception {
		Employee employee = new Employee("James Gosling", "(99)99999-9999", "gosling@email.com");
		employee.setId(1);
		
		try {
			employeeDAO.update(employee);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldDeleteEmployee () throws Exception {
		try {
			employeeDAO.delete(1);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
}