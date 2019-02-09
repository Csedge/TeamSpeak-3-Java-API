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
		CommandBuilder cmdBld = null;
		int testInt = 9;
		final TS3Config config = new TS3Config();
		config.setHost("localhost");

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		
		String str = new String(String.valueOf(Character.toChars(11)));
		
		while(Character.isValidCodePoint(testInt))
		{
			cmdBld = new CommandBuilder("sendtextmessage");
			cmdBld.addIf(true, new KeyValueParam("targetmode", TextMessageTargetMode.CHANNEL.getIndex()));
			cmdBld.addIf(true, new KeyValueParam("target", 0));
			cmdBld.addIf(true, new KeyValueParam("msg", String.valueOf(Character.toChars(testInt))));
			
			if(cmdBld.build().toString().equals("sendtextmessage targetmode=" + String.valueOf(TextMessageTargetMode.CHANNEL.getIndex()) + " target=0 msg=" + String.valueOf(Character.toChars(testInt)))) 
			{
				testInt = testInt + 1;
			}
			else
			{
				System.out.print("" + testInt + "\n");
				System.out.print(String.valueOf(Character.toChars(testInt)) + "\n");
				System.out.print(cmdBld.build().toString() + "\n");
				
				query.getAsyncApi().executeAndReturnError(cmdBld.build()).getUninterruptibly();
				//api.sendChannelMessage(String.valueOf(Character.toChars(testInt)));
				
				testInt = testInt + 1;
			}
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
