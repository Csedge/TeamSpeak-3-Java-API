package unitTests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

public class FileTransferHelperTest {
	private static final String EXAMPLE_DIRECTORY = "example-directory";
	private static final String EXAMPLE_FILE_NAME = EXAMPLE_DIRECTORY + "/Hello World.txt";
	private static final byte[] EXAMPLE_FILE_CONTENT = "Hello file transferring world!".getBytes(StandardCharsets.UTF_8);

	private static final byte[] RED_EXAMPLE_ICON;
	private static final byte[] BLUE_EXAMPLE_ICON;

	static {
		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();

		g.setColor(Color.RED);
		g.fillRect(0, 0, 16, 16);
		ByteArrayOutputStream red = new ByteArrayOutputStream(128);
		try {
			ImageIO.write(img, "PNG", red);
		} catch (IOException e) {
			throw new IOError(e);
		}
		RED_EXAMPLE_ICON = red.toByteArray();

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 16, 16);
		ByteArrayOutputStream blue = new ByteArrayOutputStream(128);
		try {
			ImageIO.write(img, "PNG", blue);
		} catch (IOException e) {
			throw new IOError(e);
		}
		BLUE_EXAMPLE_ICON = blue.toByteArray();
	}
	
	private static Map<ChannelProperty, String> mapOf(ChannelProperty property, Object value) {
		return Collections.singletonMap(property, String.valueOf(value));
	}
	
	@Test
	public void testUploadDownload() {
		final TS3Config config = new TS3Config();
		config.setHost("localhost");
		config.setEnableCommunicationsLogging(true);
		int id;
		Channel chn;

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "1234");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		api.sendChannelMessage("PutPutBot is online!");

		// Set up properties for our test channels
		final Map<ChannelProperty, String> properties = new HashMap<>();
		properties.put(ChannelProperty.CHANNEL_FLAG_SEMI_PERMANENT, "1"); // Stay until restart
		int defaultChannelId = api.whoAmI().getChannelId();
		properties.put(ChannelProperty.CPID, String.valueOf(defaultChannelId));
		properties.put(ChannelProperty.CHANNEL_TOPIC, "File transfer tests");

		// --------------------- //
		// Direct file transfers //
		// --------------------- //

		chn = api.getChannelByNameExact("Direct file transfers", false);
		if (chn != null) {
			api.deleteChannel(chn.getId());
		}
		
		// Create a new channel
		int directChannel = api.createChannel("Direct file transfers", properties);
		// Upload and set an icon
		long blackIconId = api.uploadIconDirect(RED_EXAMPLE_ICON);
		api.editChannel(directChannel, mapOf(ChannelProperty.CHANNEL_ICON_ID, blackIconId));
		// Create a new directory on the file repository
		api.createFileDirectory(EXAMPLE_DIRECTORY, directChannel);
		// And upload a file to it
		api.uploadFileDirect(EXAMPLE_FILE_CONTENT, EXAMPLE_FILE_NAME, false, directChannel);
		// Download it again and print it to System.out
		byte[] directDownload = api.downloadFileDirect(EXAMPLE_FILE_NAME, directChannel);
		System.out.println(new String(directDownload, StandardCharsets.UTF_8));

		// --------------------- //
		// Stream file transfers //
		// --------------------- //

		chn = api.getChannelByNameExact("Stream file transfers", false);
		if (chn != null) {
			api.deleteChannel(chn.getId());
		}
		
		// Create a new channel
		int streamChannel = api.createChannel("Stream file transfers", properties);
		// Upload and set an icon
		InputStream iconIn = new ByteArrayInputStream(BLUE_EXAMPLE_ICON); // Usually a FileInputStream
		long whiteIconId = api.uploadIcon(iconIn, BLUE_EXAMPLE_ICON.length);
		api.editChannel(streamChannel, mapOf(ChannelProperty.CHANNEL_ICON_ID, whiteIconId));
		// Create a new directory on the file repository
		api.createFileDirectory(EXAMPLE_DIRECTORY, streamChannel);
		// And upload a file to it
		InputStream dataIn = new ByteArrayInputStream(EXAMPLE_FILE_CONTENT); // Usually a FileInputStream
		api.uploadFile(dataIn, EXAMPLE_FILE_CONTENT.length, EXAMPLE_FILE_NAME, false, streamChannel);
		// Download it again and print it to System.out
		ByteArrayOutputStream dataOut = new ByteArrayOutputStream(128); // Usually a FileOutputStream
		api.downloadFile(dataOut, EXAMPLE_FILE_NAME, streamChannel);
		System.out.println(new String(dataOut.toByteArray(), StandardCharsets.UTF_8));

		// We're done, disconnect
		query.exit();
	}
}
