package testAPI;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class api_tests {

	@Test
	public void test() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "kdTfpo1q");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		api.sendChannelMessage("PutPutBot is online!");
	}

}
