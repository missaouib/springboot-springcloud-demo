package com.example.sticky.demo2;

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
//    private static final String DELIMITER = "$%%$";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        logger.info("netty server received message: " + message);

        String response = "hello, i am netty server. " +
                "current time is " + new Date().getTime() + "."+LINE_SEPARATOR;
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
