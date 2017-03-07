package br.edu.ifal.commanage.bo;

import org.junit.Test;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Sale;
import junit.framework.TestCase;

public class CompanyBOSaleTest extends TestCase {
	
	private CompanyBOSale companyBOSale; 
	
	@Override
	protected void setUp () throws Exception {
		companyBOSale = new CompanyBOSale(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotValidateAddSaleIfIncorrectData () throws Exception {
		Product product = new Product("Oracle", 1, 2);
		Sale sale = new Sale(product.getId(), 0);
		
		try {
			companyBOSale.validateAddSale(sale);
		} catch (Exception e) {
			assertEquals("Invalid sale", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateAddSaleIfCorrectData () throws Exception {
		Product product = new Product("Oracle", 1, 2);
		Sale sale = new Sale(product.getId(), 10);
		
		try {
			companyBOSale.validateAddSale(sale);
			// assertEquals(1, companyBOSale.getSaleQuantity());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}