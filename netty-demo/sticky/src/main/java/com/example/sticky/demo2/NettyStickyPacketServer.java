package com.example.sticky.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyStickyPacketServer {

    private static final Logger logger = LogManager.getLogger(NettyStickyPacketServer.class);
//    private static final String DELIMITER = "$%%$";
//    private static final int PING_BYTES_LENGTH = 25;

    private static final int DEFAULT_PORT = 8998;

    private int port;

    public NettyStickyPacketServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        logger.info("netty server is starting.");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
//                                    .addLast(new DelimiterBasedFrameDecoder(
//                                            1024,
//                                            Unpooled.copiedBuffer(DELIMITER.getBytes())))
//                                    .addLast(new FixedLengthFrameDecoder(PING_BYTES_LENGTH))
                                    .addLast(new StringDecoder())
                                    .addLast(new NettyStickyPacketServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("netty server started, listened[" + port + "].");
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyStickyPacketServer server = new NettyStickyPacketServer(DEFAULT_PORT);
        server.start();
    }

}
