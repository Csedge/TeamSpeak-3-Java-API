package com.github.theholywaffle.teamspeak3.Ts3Api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class ApiTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void Testmovefile(){
        TS3Api obj = new TS3Api();
        obj.moveFile("/dir1/a.txt","/dir2/b.txt",1);
        obj.moveFile("/dir1/b.txt","/dir2/b.txt",1,2);
    }
    public void Testmovequery(){
        TS3Api obj = new TS3Api();
        obj.moveQuery(1);
    }
    public void TestpokeClient(){
        TS3Api obj = new TS3Api();
        obj.pokeClient(1,"Hello World!");

    }
    public void TestRemoveClientFromServerGroup(){
        TS3Api obj = new TS3Api();
        obj.removeClientFromServerGroup(1,2);
    }

    public void Testrenamechannelgroup(){
        TS3Api obj = new TS3Api();
        obj.renameChannelGroup(1,"Hello");
        obj.renameChannelGroup("Hello","Hello_new");
    }

    public void TestrenameserverGroup(){
        TS3Api obj = new TS3Api();
        obj.renameServerGroup(1,"HelloTestCase");
        obj.renameServerGroup("HelloTestCase","New!");
    }
}
