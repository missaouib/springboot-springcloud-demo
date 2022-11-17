package com.example.manualhttp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LogManager.getLogger(NettyHttpServerHandler.class);

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String method = request.getMethod().name();
        String uri = request.getUri();
        logger.info("receives http request: " + method + " " + uri + ".");

        String html = "<html><body>hello, i am netty http server.</body></html>";
        ByteBuf byteBuf = Unpooled.copiedBuffer(html, CharsetUtil.UTF_8);

        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set("content-type", "text/html;charset=UTF-8");
        response.content().writeBytes(byteBuf);

        byteBuf.release();

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
