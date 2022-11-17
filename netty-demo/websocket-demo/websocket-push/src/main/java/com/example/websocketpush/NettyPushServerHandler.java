package com.example.websocketpush;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyPushServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LogManager.getLogger(NettyPushServerHandler.class);
    private static final int RESPONSE_CODE_OK = 200;

    private WebSocketServerHandshaker webSocketServerHandshaker;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client connection established: " + ctx.channel());
        ChannelManager.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("client disconnected: " + ctx.channel());
       ChannelManager.remove(ctx.channel());
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if(msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame) {
        // websocket网页客户端发送的是ping消息，他会不停的ping你，看看长连接是否存活和有效
        if(webSocketFrame instanceof PingWebSocketFrame) {
            logger.info("receives ping frame from client: " + ctx.channel());
            WebSocketFrame pongWebSocketFrame = new PongWebSocketFrame(
                    webSocketFrame.content().retain());
            ctx.channel().write(pongWebSocketFrame);
            return;
        }

        // websocket网页客户端发送一个请求过来，请求关闭这个websocket连接
        if(webSocketFrame instanceof CloseWebSocketFrame) {
            logger.info("receives close websocket request from client: " + ctx.channel());
            webSocketServerHandshaker.close(ctx.channel(),
                    ((CloseWebSocketFrame) webSocketFrame).retain());
            return;
        }

        // websocket网页客户端发送请求，但是他不是text文本请求
        if(!(webSocketFrame instanceof TextWebSocketFrame)) {
            logger.error("netty push server only support text frame, does not support other type frame.");
            String errorMsg = String.format("%s type frame is not supported.",
                    webSocketFrame.getClass().getName());
            throw new UnsupportedOperationException(errorMsg);
        }

        // websocket网页客户端发送一个请求过来，是text frame类型的
        String request = ((TextWebSocketFrame)webSocketFrame).text();
        logger.info("receives text frame[" + request + "] from client: " + ctx.channel());

        TextWebSocketFrame response = new TextWebSocketFrame(request);
        ChannelManager.pushToAllChannels(response);
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if(!request.decoderResult().isSuccess()
                || (!"websocket".equals(request.headers().get("Upgrade")))) {
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST
            );
            sendHttpResponse(ctx, request, response);
            return;
        }

        logger.info("receives handshake request from client: " + ctx.channel());

        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory =
                new WebSocketServerHandshakerFactory("ws://localhost:8998/push", null, false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if(webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            webSocketServerHandshaker.handshake(ctx.channel(), request);
            logger.info("netty push server handshake with client: " + ctx.channel());
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx,
                                  FullHttpRequest request,
                                  DefaultFullHttpResponse response) {
        if(response.status().code() != RESPONSE_CODE_OK) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(
                    response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            logger.info("http response is not ok: " + byteBuf.toString(CharsetUtil.UTF_8));
            byteBuf.release();
        }

        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);

        if(response.status().code() != RESPONSE_CODE_OK) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
