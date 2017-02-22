package br.edu.ifal.commanage.bo;


import junit.framework.TestCase;
import org.junit.Test;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Employee;
import br.edu.ifal.commanage.bo.CompanyBO;

public class CompanyBOEmployeeTest extends TestCase {
	
	private CompanyBO companyBO;
	
	@Override
	protected void setUp () throws Exception {
		companyBO = new CompanyBO(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotAddEmployeeIfIncorrectData () throws Exception {
		Employee employee = new Employee("gosling", "(8)9999999", "g@email.c");
		try {
			companyBO.validateAddEmployee(employee);
		} catch(Exception e) {
			assertEquals("Invalid employee", e.getMessage());
		}
	}
	
	@Test
	public void testShouldAddEmployeeIfCorrectData () throws Exception {
		Employee employee = new Employee("Gosling", "(82)99999-9999", "gosling@email.com");
		try {
			companyBO.validateAddEmployee(employee);
			assertEquals(1, companyBO.getEmployeeQuantity());
		} catch(Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}