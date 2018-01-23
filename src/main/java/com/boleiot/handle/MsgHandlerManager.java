package com.boleiot.handle;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MsgHandlerManager {
    private static MsgHandlerManager mMsgHandlerManager;
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    private MsgHandlerManager() {

    }

    public static MsgHandlerManager get() {
        if (mMsgHandlerManager == null) {
            mMsgHandlerManager = new MsgHandlerManager();
        }
        return mMsgHandlerManager;
    }

    public void put(String id, Channel channel) {
        channelMap.put(id, channel);
    }

    public Channel get(String id) {
        return channelMap.get(id);
    }

    public void remove(String id) {
        channelMap.remove(id);
    }

    public void sendMessage(String id, String msg) {
        Channel channel = get(id);
        if (channel != null) {
            String[] split = id.split(":");
            String hostName = split[0];
            int port = Integer.valueOf(split[1]);
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8),
                    new InetSocketAddress(hostName, port)));
        }
    }
}
