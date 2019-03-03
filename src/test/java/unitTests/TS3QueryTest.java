package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.theholywaffle.teamspeak3.*;

public class TS3QueryTest {
	@Test
	public void DefaultQuery() {
		TS3Query query = new TS3Query();
		Exception excp = null;
		
		assertNotNull(query.getApi());
		assertNotNull(query.getAsyncApi());
		try {
			query.connect();
		} catch(Exception e) {
			excp = e;
		}
		assertNull(excp);
		excp = null;
		try {
			query.exit();
		} catch(Exception e) {
			excp = e;
		}
		assertNull(excp);
		assertFalse(query.isConnected());
		
		
	}
	
	@Test
	public void QueryWithCustomConfig() {
		TS3Config config = new TS3Config();
		TS3Query query;
		
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		
		query = new TS3Query(config);

		assertNotNull(query.getApi());
		assertNotNull(query.getAsyncApi());
		assertFalse(query.isConnected());
		
		query.connect();
		
		assertTrue(query.isConnected());
		
		query.exit();
		
	}
}
