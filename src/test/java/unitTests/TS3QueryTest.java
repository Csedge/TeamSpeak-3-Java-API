package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;

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
		Exception excp = null;
		
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		
		query = new TS3Query(config);

		assertNotNull(query.getApi());
		assertNotNull(query.getAsyncApi());
		assertFalse(query.isConnected());
		
		query.connect();
		
		assertTrue(query.isConnected());
		
		query.exit();
		
		assertFalse(query.isConnected());
		
		try {
			query.connect();
		} catch(Exception e) {
			excp = e;
			System.out.print("GOOD: Could not connect\n");
		}
		assertNotNull(excp);
	}
	
	@Test
	public void TwoQueryWithCustomConfig() {
		//this tests any shared resources across different instances of query
		TS3Config config = new TS3Config();
		TS3Query query;
		TS3Query query2;
		Exception excp = null;
		
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		
		query = new TS3Query(config);
		query2 = new TS3Query(config);

		
		assertFalse(query.isConnected());
		assertFalse(query2.isConnected());
		
		query.connect();
		
		assertTrue(query.isConnected());
		assertFalse(query2.isConnected());
		
		query2.connect();
		query.exit();
		query2.connect(); //its behavior is intended to allow multiple connects, refreshing the QueryIO
		
		assertFalse(query.isConnected());
		assertTrue(query2.isConnected());
		
		try {
			query.connect();
		} catch(Exception e) {
			excp = e;
			System.out.print("GOOD: Could not connect\n");
		}
		
		assertNotNull(excp);
		excp = null;
		
		try {
			query2.connect();
		} catch(Exception e) {
			excp = e;
			System.out.print("BAD: Could not connect\n");
		}
		assertNull(excp);
	}
	
	@Test
	public void MultithreadQueryWithCustomConfig() {
		//this tests any shared resources across different instances of query
		TS3Config config = new TS3Config();
		final TS3Query query;
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Thread thread = null;
		
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		
		query = new TS3Query(config);
		
		
		for(int i = 0; i < 10; i = i + 1)
		{
			thread = new Thread() {
				TS3Query q = query;
				@Override
				public void run()
				{
					q.connect();
				}
			};
			thread.start();
			threads.add(thread);
		}
		
		for(Thread tmp : threads)
		{
			try {
			tmp.join();
			} catch (Exception e) {
				System.out.print(e.getMessage() + "\n");
			}
		}
		
		threads.clear();
		
		query.getApi().login("serveradmin", "1234");
		
		for(int i = 0; i < 10; i = i + 1)
		{
			thread = new Thread() {
				TS3Query q = query;
				@Override
				public void run()
				{
					q.getApi().selectVirtualServerById(1);
					q.getApi().sendChannelMessage("DERP");
					q.exit();
				}
			};
			thread.start();
			threads.add(thread);
		}
		
		for(Thread tmp : threads)
		{
			try {
			tmp.join();
			} catch (Exception e) {
				System.out.print(e.getMessage() + "\n");
			}
		}
	}
}
