package br.edu.ifal.commanage.connection;

import junit.framework.TestCase;
import org.junit.Test;
import br.edu.ifal.commanage.connection.ConnectionFactory;

public class ConnectionFactoryTest extends TestCase {
	
	@Test
	public void testShouldConnectToDatabase() throws Exception {
		assertNotNull(ConnectionFactory.getConnection());
	}
}