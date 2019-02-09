package manualTestUnits;

import org.junit.Test;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;
import com.github.theholywaffle.teamspeak3.commands.CommandBuilder;
import com.github.theholywaffle.teamspeak3.commands.Command;
import com.github.theholywaffle.teamspeak3.commands.parameter.KeyValueParam;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class manual_tests {
	@Test
	public void testCommandBuilderA()
	{
		//command builder and test integer for iterating through character codes
		CommandBuilder cmdBld = null;
		int testInt = 9;
		
		//lines 23 - 33 connect a test client to the server
		final TS3Config config = new TS3Config();
		config.setHost("localhost");

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		
		//make a string for use in tests
		String str = new String(String.valueOf(Character.toChars(11)));
		
		
		//loop through all valid character code points, testInt gets incremented each time.
		while(Character.isValidCodePoint(testInt) && (testInt < 50))
		{
			//build the command from the ground up
			cmdBld = new CommandBuilder("sendtextmessage");
			cmdBld.addIf(true, new KeyValueParam("targetmode", TextMessageTargetMode.CHANNEL.getIndex()));
			cmdBld.addIf(true, new KeyValueParam("target", 0));
			cmdBld.addIf(true, new KeyValueParam("msg", String.valueOf(Character.toChars(testInt)))); //use the string representation of the char code
			
			//if an altered command, print results
			if(!(cmdBld.build().toString().equals("sendtextmessage targetmode=" + String.valueOf(TextMessageTargetMode.CHANNEL.getIndex()) + " target=0 msg=" + String.valueOf(Character.toChars(testInt)))))
			{
				System.out.print("" + testInt + "\n");
				System.out.print(String.valueOf(Character.toChars(testInt)) + "\n");
				System.out.print(cmdBld.build().toString() + "\n");
				
				try
				{
					query.getAsyncApi().executeAndReturnError(cmdBld.build()).getUninterruptibly();
					//api.sendChannelMessage(String.valueOf(Character.toChars(testInt)));
				}
				catch(Exception e)
				{
					System.out.print(e.getMessage());
				}
			}
			else
			{
				//unaltered command, only print information if it failed as result
				try
				{
					query.getAsyncApi().executeAndReturnError(cmdBld.build()).getUninterruptibly();
				}
				catch(Exception e)
				{
					System.out.print("" + testInt + "\n");
					System.out.print(e.getMessage());
				}
			}
			testInt = testInt + 1;
		}
		
		
		//str = str.replace(String.valueOf((char) 11), "\\v");
		//api.sendChannelMessage("\v");
		
		
			
		//System.out.print(cmdBld.build().toString());
		
		
		//Command cmd = cmdBld.build();
		
		query.exit();
		//testInt = query.getAsyncApi().executeAndReturnIntProperty(cmd, "SomVal").getUninterruptibly();
		
		//System.out.print(testInt);
	}
	
	@Test
	public void testCommandBuilderB()
	{
		//command builder and test integer for iterating through character codes
		CommandBuilder cmdBld = null;
		int testInt = 9;
		
		//lines 23 - 33 connect a test client to the server
		final TS3Config config = new TS3Config();
		config.setHost("localhost");

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		
		//make a string for use in tests
		String str = new String(String.valueOf(Character.toChars(11)));
		
		str = str.replace(String.valueOf((char) 11), "\\v");
		api.sendChannelMessage(str);
		
		
		query.exit();
		//testInt = query.getAsyncApi().executeAndReturnIntProperty(cmd, "SomVal").getUninterruptibly();
		
		//System.out.print(testInt);
	}
	
	@Test
	public void testStressServerStopStart()
	{
		final TS3Config config = new TS3Config();
		TS3Api api;
		int i = 0;
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		// Use default exponential backoff reconnect strategy
		config.setReconnectStrategy(ReconnectStrategy.exponentialBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.linearBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.constantBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.userControlled());

		// Make stuff run every time the query (re)connects
		config.setConnectionHandler(new ConnectionHandler() {

			@Override
			public void onConnect(TS3Query ts3Query) {
				TS3Api api = ts3Query.getApi();
				
				api.login("serveradmin", "1234");
				api.selectVirtualServerById(1);
				api.setNickname("PutPutBot");
				api.deleteChannel(api.getChannelByNameExact("PutPut Channel", true).getId());
		
				System.out.print("CONNECTING... \n");
			}

			@Override
			public void onDisconnect(TS3Query ts3Query) {
				// Nothing
			}
		});

		final TS3Query query = new TS3Query(config);

		query.connect();
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		api = query.getApi();
		
		while(i < 20)
		{
			api.stopServer(1);
			/*
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}
			*/
			api.startServer(1);
			i = i + 1;
		}
		api.selectVirtualServerById(1);
		
		// Let's customize our channel
		final Map<ChannelProperty, String> properties = new HashMap<>();
		properties.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
		int defaultChannelId = api.whoAmI().getChannelId();
		properties.put(ChannelProperty.CPID, String.valueOf(defaultChannelId));
		properties.put(ChannelProperty.CHANNEL_TOPIC, "PutPut discussion");

		// Done customizing, let's create the channel with our properties
		api.createChannel("PutPut Channel", properties);
		
		/*
		try
		{
			Thread.sleep(50000);
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		*/
		// Disconnect once we're done
		query.exit();
	
	}
	
	@Test
	public void testReconnect()
	{
		final TS3Config config = new TS3Config();
		TS3Api api;
		int i = 0;
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		// Use default exponential backoff reconnect strategy
		config.setReconnectStrategy(ReconnectStrategy.exponentialBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.linearBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.constantBackoff());
		//config.setReconnectStrategy(ReconnectStrategy.userControlled());

		// Make stuff run every time the query (re)connects
		config.setConnectionHandler(new ConnectionHandler() {

			@Override
			public void onConnect(TS3Query ts3Query) {
				TS3Api api = ts3Query.getApi();
				
				api.login("serveradmin", "1234");
				api.selectVirtualServerById(1);
				api.setNickname("PutPutBot");
				//api.deleteChannel(api.getChannelByNameExact("PutPut Channel", true).getId());
		
				System.out.print("CONNECTED \n");
			}

			@Override
			public void onDisconnect(TS3Query ts3Query) {
				// Nothing
			}
		});

		final TS3Query query = new TS3Query(config);

		query.connect();
		try
		{
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		
			
		// Disconnect once we're done
		query.exit();
	
	}
}
