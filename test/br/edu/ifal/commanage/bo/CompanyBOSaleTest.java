package br.edu.ifal.commanage.bo;

import org.junit.Test;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Sale;
import junit.framework.TestCase;

public class CompanyBOSaleTest extends TestCase {
	
	private CompanyBO companyBO; 
	
	@Override
	protected void setUp () throws Exception {
		companyBO = new CompanyBO(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotValidateAddSaleIfIncorrectData() throws Exception {
		Product product = new Product("name", 1, 2);
		Sale sale = new Sale(product, 0);
		try {
			companyBO.validateAddSale(sale);
		} catch (Exception e) {
			assertEquals("Invalid sale", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateAddSaleIfCorrectData() throws Exception {
		Product product = new Product("name", 1, 2);
		Sale sale = new Sale(product, 10);
		try {
			companyBO.validateAddSale(sale);
			assertEquals(1, companyBO.getSaleQuantity());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}
