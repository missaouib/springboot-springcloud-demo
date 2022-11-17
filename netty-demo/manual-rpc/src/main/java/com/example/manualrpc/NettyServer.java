package com.example.manualrpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        // 肯定需要线程池，有的线程可以去负责跟大量的客户端建立网络连接
        // 有的线程可以去负责网络io数据读写
        // 网络io数据读取出来以后，处理请求数据，数据处理也需要专门的线程去处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 默认的线程池的大小，cpu core * 2
        EventLoopGroup workerGroup = new NioEventLoopGroup(20);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new com.example.manualrpc.NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("starting netty server......");
        int port = 8998;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new NettyServer(port).start();
    }

}
