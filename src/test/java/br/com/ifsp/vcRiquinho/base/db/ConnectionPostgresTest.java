package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;

public class ConnectionPostgresTest {
	
	@Test
	void connectionTest() {
		ConnectionPostgress dbConnection = new ConnectionPostgress();
		
		assertDoesNotThrow(() -> {
			dbConnection.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST, ConnectionPostgress.DEFAULT_USER_DBTEST, ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
		});
		
		assertNotEquals(null, dbConnection.getConnection());
		
	}
	
}
