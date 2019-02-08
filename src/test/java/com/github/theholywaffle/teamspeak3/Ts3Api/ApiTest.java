package com.github.theholywaffle.teamspeak3.Ts3Api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;

import javax.imageio.ImageIO;
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

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class ApiTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void Testmovefile(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
       obj.selectVirtualServerById(1);
       obj.moveFile("/dir1/1.txt","/dir2/aasdd.txt",1);
    }
    @Test
    public void Testmovequery(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.selectVirtualServerById(1);
        obj.moveQuery(2);
    }
    @Test
    public void TestpokeClient(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.selectVirtualServerById(1);
        obj.pokeClient(1,"Hello World!");
    }

    @Test
    public void Testrenamechannelgroup(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.selectVirtualServerById(1);
        obj.renameChannelGroup(1,"Hello");

    }
    @Test
    public void TestsendChannelMsg(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.selectVirtualServerById(1);
        obj.sendChannelMessage("ChannelMsgTest");
    }
    @Test
    public void TestSendPrivateMessage(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.selectVirtualServerById(1);
        obj.login("serveradmin","FyXmnFvP");
        obj.sendPrivateMessage(1,"Hello");
    }
    @Test
    public void TestSendSeverMsg(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.selectVirtualServerById(1);
        obj.login("serveradmin","FyXmnFvP");
        obj.sendServerMessage("Server Msg Test");
    }
    @Test
    public void TestSetMsgRed(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        //obj.setMessageRead("100");
    }
    @Test
    public void TestSetNickname(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.selectVirtualServerById(1);
        obj.setNickname("NewNickName");
    }
    @Test
    public void TestUploadFiles(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.selectVirtualServerById(1);
        obj.login("serveradmin","FyXmnFvP");
        byte[] EXAMPLE_FILE_CONTENT = "Hello file transferring world!".getBytes(StandardCharsets.UTF_8);
        obj.uploadFileDirect(EXAMPLE_FILE_CONTENT,"/dir1/TestUploadeFiles",true,1);

    }
   @Test
    public void TestStopServer(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
        final TS3Query query = new TS3Query(config);
        query.connect();
        TS3Api obj = query.getApi();
        obj.login("serveradmin","FyXmnFvP");
        obj.stopServer(1,"TestStopServer");
    }

}
