package br.edu.ifal.commanage.bo;

import org.junit.Test;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.model.Manager;
import br.edu.ifal.commanage.model.Salesperson;
import junit.framework.TestCase;

public class CompanyBOEmployeeTest extends TestCase {
	
	private CompanyBOEmployee companyBOEmployee;
	
	@Override
	protected void setUp() throws Exception {
		companyBOEmployee = new CompanyBOEmployee(new Company());
		super.setUp();
	}
	
	@Test
	public void testNotShouldAddEmployeeIfIncorrectData() throws Exception {
		Employee employee = new Manager("gosling", "(8)9999999", "g@email.c");
		
		try {
			companyBOEmployee.validateAddEmployee(employee);
		} catch (Exception e) {
			assertEquals("Invalid employee", e.getMessage());
		}
	}
	
	@Test
	public void testShouldAddEmployeeIfCorrectData() throws Exception {
		Employee employee = new Salesperson("Gosling", "(82)99999-9999", "gosling@email.com");
		
		try {
			companyBOEmployee.validateAddEmployee(employee);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}