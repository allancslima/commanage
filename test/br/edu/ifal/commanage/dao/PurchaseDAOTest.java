package br.edu.ifal.commanage.dao;

import org.junit.Test;

import br.edu.ifal.commanage.model.Purchase;
import junit.framework.TestCase;

public class PurchaseDAOTest extends TestCase {
	
	PurchaseDAO purchaseDAO = new PurchaseDAO();
	
	@Test
	public void testShouldCreatePurchase () throws Exception {
		Purchase purchase = new Purchase(1, 10);

		try {
			purchaseDAO.create(purchase);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldReadPurchases () throws Exception {
		try {
			assertNotNull(purchaseDAO.read());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldDeletePurchase () throws Exception {
		try {
			purchaseDAO.delete(1);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}