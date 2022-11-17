package com.example.manualrpc.rpc.service;

import com.example.manualrpc.rpc.RpcDecoder;
import com.example.manualrpc.rpc.RpcEncoder;
import com.example.manualrpc.rpc.RpcRequest;
import com.example.manualrpc.rpc.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NettyRpcServer {

    private static final Logger logger = LogManager.getLogger(NettyRpcServer.class);
    private static final int DEFAULT_PORT = 8998;

    private List<ServiceConfig> serviceConfigs = new CopyOnWriteArrayList<ServiceConfig>();
    private int port;

    public NettyRpcServer(int port) {
        this.port = port;
    }

    public void start() {
        logger.info("netty rpc server starting......");

        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new RpcDecoder(RpcRequest.class))
                                    .addLast(new RpcEncoder(RpcResponse.class))
                                    .addLast(new NettyRpcServerHandler(serviceConfigs));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 到这一步为止，server启动了而且监听指定的端口号了
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("netty rpc server started successfully, listened[" + port + "]");
            // 进入一个阻塞的状态，同步一直等待到你的server端要关闭掉
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("netty rpc server failed to start, listened[" + port + "]");
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }
    }

    public void addServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfigs.add(serviceConfig);
    }

    public static void main(String[] args) {
        ServiceConfig serviceConfig = new ServiceConfig("TestService",
                TestService.class, TestServiceImpl.class);

        NettyRpcServer nettyRpcServer = new NettyRpcServer(DEFAULT_PORT);
        nettyRpcServer.addServiceConfig(serviceConfig);
        nettyRpcServer.start();
    }

}
