package br.edu.ifal.commanage.bo;

import junit.framework.TestCase;
import org.junit.Test;
import br.edu.ifal.commanage.model.Company;

public class CompanyBOTest extends TestCase {
	
	private CompanyBO companyBO;
	
	@Override
	protected void setUp () throws Exception {
		companyBO = new CompanyBO(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotValidateSetCompanyName () throws Exception {
		try {
			companyBO.validateSetCompanyName("oracle");
			assertEquals("", companyBO.getName());
		} catch(Exception e) {
			assertEquals("Invalid name", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateSetCompanyName () throws Exception {
		try {
			companyBO.validateSetCompanyName("Oracle");
			assertEquals("Oracle", companyBO.getName());
		} catch(Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldNotValidateSetCompanyCnpj () throws Exception {
		try {
			companyBO.validateSetCompanyCnpj("11111111111111");
			assertEquals("", companyBO.getCnpj());
		} catch(Exception e) {
			assertEquals("Invalid cnpj", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateSetCompanyCnpj () throws Exception {
		try {
			companyBO.validateSetCompanyCnpj("11.111.111/1111.11");
			assertEquals("11.111.111/1111.11", companyBO.getCnpj());
		} catch(Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldNotValidateSetCompanyPhone () throws Exception {
		try {
			companyBO.validateSetCompanyPhone("82999999999");
			assertEquals("", companyBO.getPhone());
		} catch(Exception e) {
			assertEquals("Invalid phone", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateSetCompanyPhone () throws Exception {
		try {
			companyBO.validateSetCompanyPhone("(82)99999-9999");
			assertEquals("(82)99999-9999", companyBO.getPhone());
		} catch(Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}