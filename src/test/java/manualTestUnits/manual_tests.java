package manualTestUnits;

import org.junit.Test;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.commands.CommandBuilder;
import com.github.theholywaffle.teamspeak3.commands.Command;
import com.github.theholywaffle.teamspeak3.commands.parameter.KeyValueParam;


import static org.junit.Assert.*;

public class manual_tests {
	@Test
	public void testCommandBuilder()
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
		while(Character.isValidCodePoint(testInt))
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
}
