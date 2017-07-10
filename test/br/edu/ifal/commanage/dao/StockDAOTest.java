package br.edu.ifal.commanage.dao;

import org.junit.Test;

import junit.framework.TestCase;

public class StockDAOTest extends TestCase {
	
	StockDAO stockDAO = new StockDAO();
	
	@Test
	public void testShouldCreateStock() throws Exception {
		try {
			stockDAO.create(1, 10);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldReadStock() throws Exception {
		try {
			assertNotNull(stockDAO.read());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldUpdateStock() throws Exception {
		try {
			stockDAO.update(1, 20);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldDeleteSale() throws Exception {
		try {
			stockDAO.delete(2);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}