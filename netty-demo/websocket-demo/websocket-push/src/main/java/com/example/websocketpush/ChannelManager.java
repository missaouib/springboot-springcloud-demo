package com.example.websocketpush;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {

    private static ChannelGroup channelGroup =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ConcurrentHashMap<String, ChannelId> channelIds =
            new ConcurrentHashMap<String, ChannelId>();

    public static void add(Channel channel) {
        channelGroup.add(channel);
        channelIds.put(channel.id().asShortText(), channel.id());
    }

    public static void remove(Channel channel) {
        channelGroup.remove(channel);
        channelIds.remove(channel.id().asShortText());
    }

    public static Channel get(String id) {
        return channelGroup.find(channelIds.get(id));
    }

    public static void pushToAllChannels(TextWebSocketFrame webSocketFrame) {
        channelGroup.writeAndFlush(webSocketFrame);
    }

}
