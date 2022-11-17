package com.example.manualrpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 网络连接tcp三次握手后，建立和封装一个channel，网络连接的通信管道
    // 此时这个channel应该就是可以实现一个激活
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active......");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel read: " + (String)msg);

        String response = "hello world......";
        ByteBuf responseByteBuf = Unpooled.buffer();
        responseByteBuf.writeBytes(response.getBytes());

        ctx.channel().writeAndFlush(responseByteBuf);
        System.out.println("channel write: " + response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel read complete......");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();;
        ctx.close();
    }
}
