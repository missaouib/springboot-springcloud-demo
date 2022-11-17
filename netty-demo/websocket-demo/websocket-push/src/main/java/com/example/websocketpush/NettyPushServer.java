package com.example.websocketpush;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyPushServer {

    private static final Logger logger = LogManager.getLogger(NettyPushServer.class);
    private static final int DEFAULT_PORT = 8998;

    private int port;

    public NettyPushServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        logger.info("netty push server is starting.");

        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("logging", new LoggingHandler("DEBUG"))
                                    .addLast("http-codec", new HttpServerCodec())
                                    .addLast("aggregator", new HttpObjectAggregator(65536))
                                    .addLast("http-chunked", new ChunkedWriteHandler())
                                    .addLast("ntty-push-server-handler", new NettyPushServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("netty push server is started, listened[" + port + "].");

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyPushServer nettyHttpServer = new NettyPushServer(DEFAULT_PORT);
        nettyHttpServer.start();
    }

}
