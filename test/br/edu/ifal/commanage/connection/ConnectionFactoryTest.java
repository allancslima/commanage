package br.edu.ifal.commanage.connection;

import java.sql.Connection;

import org.junit.Test;

import junit.framework.TestCase;

public class ConnectionFactoryTest extends TestCase {
	
	@Test
	public void testShouldConnectToDatabase () throws Exception {
		try {
			Connection con = ConnectionFactory.getConnection();
			assertNotNull(con);
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void testShouldCloseConnection () throws Exception {
		try {
			Connection con = ConnectionFactory.getConnection();
			assertNotNull(con);
			ConnectionFactory.closeConnection(con);
			assertEquals(true, con.isClosed());
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}
}