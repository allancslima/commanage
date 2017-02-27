package br.edu.ifal.commanage.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import br.edu.ifal.commanage.model.Product;

public class ProductDAOTest {
	
	ProductDAO productDAO = new ProductDAO();
	
	@Test
	public void testShouldCreateEmployee () throws Exception {
		Product product = new Product("Oracle", 10.0, 15.0);

		try {
			productDAO.create(product);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldReadEmployees () throws Exception {
		try {
			assertNotNull(productDAO.read());
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldUpdateEmployee () throws Exception {
		Product product = new Product("Oracle", 10.0, 14.0);
		product.setId(1);
		
		try {
			productDAO.update(product);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void testShouldDeleteEmployee () throws Exception {
		try {
			productDAO.delete(1);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
}