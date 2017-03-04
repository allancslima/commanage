package br.edu.ifal.commanage.dao;

import junit.framework.TestCase;
import org.junit.Test;
import br.edu.ifal.commanage.model.Sale;

public class SaleDAOTest extends TestCase {
	
	SaleDAO saleDAO = new SaleDAO();
	
	@Test
	public void testShouldCreateSales () throws Exception {
		Sale sale = new Sale(1, 10);

		try {
			saleDAO.create(sale);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldReadSales () throws Exception {
		try {
			assertNotNull(saleDAO.read());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldUpdateSale () throws Exception {
		Sale sale = new Sale(1, 10);
		sale.setId(0);
		
		try {
			saleDAO.update(sale);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldDeleteSale () throws Exception {
		try {
			saleDAO.delete(1);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}