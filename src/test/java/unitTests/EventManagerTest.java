package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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
		api.registerAllEvents();
		
		//adding all of them should not throw error
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
		
		query.exit();
	}
	
	@Test
	public void runEventListenerTest() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);	
		api.registerEvent(TS3EventType.TEXT_CHANNEL, 0);
		
		
		api.addTS3Listeners(new TS3EventAdapter() {

			@Override
			public void onTextMessage(TextMessageEvent e) {
				System.out.println("Text message received in " + e.getTargetMode());
			}

		});
		
		//this should trigger listener above
		api.sendChannelMessage("TEST");
		
		query.exit();
	}
	
	@Test
	public void removeEventListenerTest() {
		final TS3Listener listen;
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);	
		api.registerEvent(TS3EventType.TEXT_CHANNEL, 0);
		
		listen = new TS3EventAdapter() {

		};
		
		api.addTS3Listeners(listen);
		//removing should not throw error
		api.removeTS3Listeners(listen);
		
		query.exit();
	}
	
	@Test
	public void testEventTypes() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ChannelProperty.CID.getName(), "1");
		map.put(ChannelProperty.CHANNEL_ORDER.getName(), "1");
		map.put(ChannelProperty.CPID.getName(), "1");
		Wrapper wrap = new Wrapper(map);
		
		ChannelMovedEvent chan = new ChannelMovedEvent(wrap);
		
		TextMessageEvent txt = new TextMessageEvent(wrap);
		
		ServerEditedEvent srvEdit = new ServerEditedEvent(wrap);
		
		ClientMovedEvent clientMoved = new ClientMovedEvent(wrap);

		ClientLeaveEvent clientLeaved = new ClientLeaveEvent(wrap);

		ClientJoinEvent clientJoined = new ClientJoinEvent(wrap);

		ChannelEditedEvent clientEdited = new ChannelEditedEvent(wrap);

		ChannelDescriptionEditedEvent clientDescription = new ChannelDescriptionEditedEvent(wrap);

		ChannelCreateEvent channelCreate = new ChannelCreateEvent(wrap) ;

		ChannelDeletedEvent channelDeleted = new ChannelDeletedEvent(wrap);

		ChannelMovedEvent channelMovedEvent = new ChannelMovedEvent(wrap);

		ChannelPasswordChangedEvent channelPasswordChanged = new ChannelPasswordChangedEvent(wrap);

		PrivilegeKeyUsedEvent privelegedKeyUsed = new PrivilegeKeyUsedEvent (wrap);
		
		assertTrue((chan.getChannelId() == 1) && (chan.getChannelOrder() == 1) && (chan.getChannelParentId() == 1));
		
	}
}
