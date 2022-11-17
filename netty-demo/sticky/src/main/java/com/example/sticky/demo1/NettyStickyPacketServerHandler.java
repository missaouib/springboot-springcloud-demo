package com.example.sticky.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class NettyStickyPacketServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(NettyStickyPacketServerHandler.class);
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String message = new String(bytes,"UTF-8");
        message = message.substring(0,message.length() - LINE_SEPARATOR.length());
        logger.info("netty server received message: " + message);

        String response = "hello, i am netty server. " +
                "current time is " + new Date().getTime() + "." + LINE_SEPARATOR;
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(responseByteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
