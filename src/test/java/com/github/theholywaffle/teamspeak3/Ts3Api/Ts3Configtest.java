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
public class Ts3Configtest{
    @Test
    public void Testsethost(){
        final TS3Config config = new TS3Config();
        config.setHost("127.0.0.1");
        config.setEnableCommunicationsLogging(true);
    }
    @Test
    public void TestSetTimeout(){
        final TS3Config config = new TS3Config();
        config.setCommandTimeout(30);
    }
    @Test
    public void TestSetport(){
        final TS3Config config = new TS3Config();
        config.setQueryPort(1111);
    }
    @Test
    public void Testlog(){
        final TS3Config config = new TS3Config();
        config.setEnableCommunicationsLogging(true);
    }
}
