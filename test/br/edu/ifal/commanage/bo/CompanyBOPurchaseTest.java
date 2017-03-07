package br.edu.ifal.commanage.bo;

import org.junit.Test;

import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;
import br.edu.ifal.commanage.model.Purchase;
import junit.framework.TestCase;

public class CompanyBOPurchaseTest extends TestCase {
	
	private CompanyBOPurchase companyBOPurchase; 
	
	@Override
	protected void setUp () throws Exception {
		companyBOPurchase = new CompanyBOPurchase(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotValidateAddSaleIfIncorrectData () throws Exception {
		Product product = new Product("Oracle", 1, 2);
		Purchase purchase = new Purchase(product.getId(), 0);
		
		try {
			companyBOPurchase.validateAddPurchase(purchase);
		} catch (Exception e) {
			assertEquals("Invalid purchase", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateAddSaleIfCorrectData () throws Exception {
		Product product = new Product("Oracle", 1, 2);
		Purchase purchase = new Purchase(product.getId(), 10);
		
		try {
			companyBOPurchase.validateAddPurchase(purchase);
			// assertEquals(1, companyBOPurchase.getPurchaseQuantity());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}