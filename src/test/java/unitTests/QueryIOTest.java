package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.github.theholywaffle.teamspeak3.QueryIO;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDeletedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ChannelPasswordChangedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;
import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Wrapper;
import com.github.theholywaffle.teamspeak3.api.wrapper.Ban;

public class QueryIOTest {
	@Test
	public void DefaultQueryIO() {
	//i would have to change queryIO to make it publicly testable, thus I have to do indirect testing.
	}
	
	@Test
	public void triggerQueryIOCommand() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		query.exit();
	}
	
	@Test
	public void triggerQueryIOResult() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		List<Ban> bans = null;
		Ban aban = null;

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.addBan(null, "SATAN", null, 120, "EVIL");
		bans = api.getBans();
		assertFalse(bans.isEmpty());
		for (Ban ban : bans) {
			if (ban.getBannedName().equals("SATAN"))
			{
				aban = ban;
				break;
			}
		}
		assertNotNull(aban);
		query.exit();
	}
}
