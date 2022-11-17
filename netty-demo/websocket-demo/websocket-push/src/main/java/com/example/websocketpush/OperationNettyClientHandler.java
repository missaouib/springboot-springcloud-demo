package com.example.websocketpush;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperationNettyClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LogManager.getLogger(OperationNettyClientHandler.class);

    private WebSocketClientHandshaker webSocketClientHandshaker;
    private ChannelFuture channelFuture;

    public OperationNettyClientHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this.webSocketClientHandshaker = webSocketClientHandshaker;
    }

    public ChannelFuture channelFuture() {
        return channelFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        webSocketClientHandshaker.handshake(ctx.channel());
        logger.info("operation netty client send websocket handshake request.");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("netty client disconnected.");
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();

        if(!webSocketClientHandshaker.isHandshakeComplete()) {
            try {
                webSocketClientHandshaker.finishHandshake(channel, (FullHttpResponse) msg);
                logger.info("netty client connected.");
                ((ChannelPromise)channelFuture).setSuccess();
            } catch(WebSocketHandshakeException e) {
                logger.error("websocket handshake failed.", e);
                ((ChannelPromise)channelFuture).setFailure(e);
            }
            return;
        }

        if(msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException("not supported http response.");
        }

        WebSocketFrame webSocketFrame = (WebSocketFrame) msg;
        if(webSocketFrame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) webSocketFrame;
            logger.info("receives text frame: " + textWebSocketFrame.text());
        } else if(webSocketFrame instanceof PongWebSocketFrame) {
            logger.info("receives pong frame: " + webSocketFrame);
        } else if(webSocketFrame instanceof CloseWebSocketFrame) {
            logger.info("receives close websocket frame, netty client is closing.");
            channel.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("operation netty client handler exception caught.", cause);
        if(!channelFuture.isDone()) {
            ((ChannelPromise)channelFuture).setFailure(cause);
        }
        ctx.close();
    }
}
