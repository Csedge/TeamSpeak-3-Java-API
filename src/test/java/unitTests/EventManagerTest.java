package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
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
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public class EventManagerTest {

	@Test
	public void addEventListenersTest() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		api.sendChannelMessage("PutPutBot is online!");

		api.registerAllEvents();
		api.addTS3Listeners(new TS3Listener() {

			@Override
			public void onTextMessage(TextMessageEvent e) {
				System.out.println("Text message received in " + e.getTargetMode());
			}

			@Override
			public void onServerEdit(ServerEditedEvent e) {
				System.out.println("Server edited by " + e.getInvokerName());
			}

			@Override
			public void onClientMoved(ClientMovedEvent e) {
				System.out.println("Client has been moved " + e.getClientId());
			}

			@Override
			public void onClientLeave(ClientLeaveEvent e) {
				// ...
			}

			@Override
			public void onClientJoin(ClientJoinEvent e) {
				// ...
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent e) {
				// ...
			}

			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent e) {
				// ...
			}

			@Override
			public void onChannelCreate(ChannelCreateEvent e) {
				// ...
			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent e) {
				// ...
			}

			@Override
			public void onChannelMoved(ChannelMovedEvent e) {
				// ...
			}

			@Override
			public void onChannelPasswordChanged(ChannelPasswordChangedEvent e) {
				// ...
			}

			@Override
			public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent e) {
				// ...
			}
		});
		
	}
}
