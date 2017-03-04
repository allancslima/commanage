package br.edu.ifal.commanage.bo;

import junit.framework.TestCase;
import org.junit.Test;
import br.edu.ifal.commanage.model.Company;
import br.edu.ifal.commanage.model.Product;

public class CompanyBOProductTest extends TestCase {

	private CompanyBOProduct companyBOProduct; 
	
	@Override
	protected void setUp () throws Exception {
		companyBOProduct = new CompanyBOProduct(new Company());
		super.setUp();
	}
	
	@Test
	public void testShouldNotValidateAddProductIfIncorrectData () throws Exception {
		Product product = new Product("oracle", 0, -1);
		
		try {
			companyBOProduct.validateAddProduct(product);
		} catch (Exception e) {
			assertEquals("Invalid product", e.getMessage());
		}
	}
	
	@Test
	public void testShouldValidateAddProductIfCorrectData () throws Exception {
		Product product = new Product("Oracle", 1.5, 2.0);
		
		try {
			companyBOProduct.validateAddProduct(product);
			// assertEquals(1, companyBOProduct.getProductQuantity());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}