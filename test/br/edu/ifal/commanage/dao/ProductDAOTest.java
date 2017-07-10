package br.edu.ifal.commanage.dao;

import org.junit.Test;

import br.edu.ifal.commanage.model.Product;
import junit.framework.TestCase;

public class ProductDAOTest extends TestCase {
	
	ProductDAO productDAO = new ProductDAO();
	
	@Test
	public void testShouldCreateEmployee() throws Exception {
		Product product = new Product("Oracle", "DB", 10.0, 15.0);

		try {
			productDAO.create(product);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldReadEmployees() throws Exception {
		try {
			assertNotNull(productDAO.read());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldUpdateEmployee() throws Exception {
		Product product = new Product("Oracle", "DB", 10.0, 14.0);
		product.setId(1);
		
		try {
			productDAO.update(product);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldDeleteEmployee() throws Exception {
		try {
			productDAO.delete(2);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}