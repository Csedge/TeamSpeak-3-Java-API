package testAPI;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class api_tests {

	@Test
	public void test() {
		/*
		final TS3Config config = new TS3Config();
		config.setHost("localhost");

		final TS3Query query = new TS3Query(config);
		query.connect();

		final TS3Api api = query.getApi();
		api.login("serveradmin", "kdTfpo1q");
		api.selectVirtualServerById(1);
		api.setNickname("PutPutBot");
		api.sendChannelMessage("PutPutBot is online!");
		
		api.addBan(ip, name, uid, timeInSeconds, reason);
		api.addBan(ip, name, uid, myTSId, timeInSeconds, reason);
		api.addChannelClientPermission(channelId, clientDBId, permName, permValue);
		api.addChannelGroup(name);
		api.addChannelGroup(name, type);
		api.addChannelGroupPermission(groupId, permName, permValue);
		api.addChannelPermission(channelId, permName, permValue);
		api.addClientPermission(clientDBId, permName, value, skipped);
		api.addClientToServerGroup(groupId, clientDatabaseId);
		api.addComplaint(clientDBId, message);
		api.addPermissionToAllServerGroups(type, permName, value, negated, skipped);
		api.addPrivilegeKey(type, groupId, channelId, description);
		api.addPrivilegeKeyChannelGroup(channelGroupId, channelId, description);
		api.addPrivilegeKeyChannelGroup(channelGroupId, channelId, description);
		api.addPrivilegeKeyServerGroup(serverGroupId, description);
		api.addServerGroup(name);
		api.addServerGroup(name, type);
		api.addServerGroupPermission(groupId, permName, value, negated, skipped);
		api.addTS3Listeners(listeners);
		api.banClient(clientId, timeInSeconds);
		api.banClient(clientId, reason);
		api.banClient(clientId, timeInSeconds, reason);
		api.broadcast(message);
		api.copyChannelGroup(sourceGroupId, targetGroupId, type);
		api.copyChannelGroup(sourceGroupId, targetName, type);
		api.copyServerGroup(sourceGroupId, targetGroupId, type);
		api.copyServerGroup(sourceGroupId, targetName, type);
		api.createChannel(name, options);
		api.createFileDirectory(directoryPath, channelId);
		api.createFileDirectory(directoryPath, channelId, channelPassword);
		api.createServer(name, options);
		api.createServerSnapshot();
		api.deleteAllBans();
		api.deleteAllComplaints(clientDBId);;
		api.deleteBan(banId);
		api.deleteChannel(channelId);
		api.deleteChannel(channelId, force);
		api.deleteChannelClientPermission(channelId, clientDBId, permName);
		api.deleteChannelGroup(groupId);
		api.deleteChannelGroup(groupId, force);
		api.deleteChannelGroupPermission(groupId, permName);
		api.deleteChannelPermission(channelId, permName);
		api.deleteClientPermission(clientDBId, permName);
		api.deleteComplaint(targetClientDBId, fromClientDBId);
		api.deleteCustomClientProperty(clientDBId, key);
		api.deleteDatabaseClientProperties(clientDBId);
		api.deleteFile(filePath, channelId);
		api.deleteFile(filePath, channelId, channelPassword);
		api.deleteFiles(filePaths, channelId, channelPassword);
		api.deleteFiles(filePaths, channelId);
		api.deleteIcon(iconId);
		api.deleteIcons(iconIds);
		api.deleteOfflineMessage(messageId);
		api.deletePermissionFromAllServerGroups(type, permName);
		api.deletePrivilegeKey(token);
		api.deleteServer(serverId);
		api.deleteServerGroup(groupId);
		api.deleteServerGroup(groupId, force);
		api.deleteServerGroupPermission(groupId, permName);
		api.deployServerSnapshot(snapshot);
		api.deployServerSnapshot(snapshot);
		api.downloadFile(dataOut, filePath, channelId);
		api.downloadFile(dataOut, filePath, channelId, channelPassword);
		api.downloadFileDirect(filePath, channelId);
		api.downloadIcon(dataOut, iconId);
		api.downloadIconDirect(iconId);
		api.editChannel(channelId, options);
		api.editChannel(channelId, property, value);
		api.editClient(clientId, options);
		api.editClient(clientId, property, value);
		api.editServer(options);
		api.editDatabaseClient(clientDBId, options);
		api.editInstance(property, value);
		api.getBans();
		api.getBindings();
		api.getChannelByNameExact(name, ignoreCase);
		api.getChannelClientPermissions(channelId, clientDBId);
		api.getChannelGroupClients(channelId, clientDBId, groupId);
		api.getChannelGroupClientsByChannelGroupId(groupId);
		api.getChannelGroupClientsByChannelId(channelId);
		api.getChannelGroupClientsByClientDBId(clientDBId);
		api.getChannelGroupPermissions(groupId);
		api.getChannelGroups();
		api.getChannelInfo(channelId);
		api.getChannelPermissions(channelId);
		api.getChannels();
		api.getChannelsByName(name);
		api.getClientByNameExact(name, ignoreCase);
		api.getClientByUId(clientUId);
		api.getClientInfo(clientId);
		api.getClientPermissions(clientDBId);
		api.getClientPermissions(clientDBId);
		api.getClients();
		api.getClientsByName(name);
		*/
	}

}
