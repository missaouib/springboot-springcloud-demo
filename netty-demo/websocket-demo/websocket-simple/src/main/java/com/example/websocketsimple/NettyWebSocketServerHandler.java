package com.example.websocketsimple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = LogManager.getLogger(NettyWebSocketServerHandler.class);

    private static ChannelGroup websocketClients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // websocket网页代码里发送过来的数据
        String request = msg.text();
        logger.info("netty server receives request: " + request + ".");

        TextWebSocketFrame response = new TextWebSocketFrame(
                "hello, i am netty server."
        );
        ctx.writeAndFlush(response);
    }

    // 如果一个网页websocket客户端跟netty server建立了连接之后，触发我们的这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        websocketClients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("websocket client is closed, channel id: " +
                ctx.channel().id().asLongText() + "[" +
                ctx.channel().id().asShortText() + "]");
    }
}
