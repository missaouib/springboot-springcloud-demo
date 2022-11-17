package com.example.websocketsimple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyWebSocketServer {

    private static final Logger logger = LogManager.getLogger(NettyWebSocketServer.class);
    private static final int DEFAULT_PORT = 8998;

    private int port;

    public NettyWebSocketServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        logger.info("netty websocket server is starting.");

        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 你的字节数组数据进来以后，第一步，先靠他去做一个处理
                                    // websocket协议（底层基于http协议）组织数据字节数组过来，先用http协议来去做一个处理
                                    // 字节数组数据先用http协议来处理，把字节数组转换为一个HttpRequest请求对象
                                    .addLast(new HttpServerCodec())
                                    // chunked write，是大数据流的分chunk块来去写出去，数据实在是太大了以后，必须得分chunk
                                    .addLast(new ChunkedWriteHandler())
                                    // 如果说你想要让很多http不要拆分成很多段过来，我要把完整的请求数据聚合到一起再给我
                                    .addLast(new HttpObjectAggregator(1024 * 32))
                                    // 基于你前面已经转换好的请求数据对象，会在这里基于websocket协议再次做一个处理
                                    // 基于http协议传输过来的数据，封装内容，可以是按websocket协议封装的http请求里的数据
                                    // 我必须在这里提取http请求里面的数据，按照websocket协议来进行解析处理，把数据提取出来作为websocket的数据片段
                                    .addLast(new WebSocketServerProtocolHandler("/websocket"))
                                    // 响应数据输出的时候，顺序是反的，第一步原始数据必须先经过websocket协议转换
                                    // websocket协议数据，必须经过http协议处理，把他最终是encode编码成一个http协议的响应数据
                                    // http响应数据序列化成字节数组，传输给浏览器
                                    // 浏览器拿到一个字节数组以后，反序列化，拿到一个http协议响应数据，提取出来内容，再按照websocket协议来处理
                                    // 最终把普通的数据给websocket他的代码
                                    .addLast("ntty-websocket-server-handler", new NettyWebSocketServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("netty websocket server is started, listened[" + port + "].");

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyWebSocketServer nettyHttpServer = new NettyWebSocketServer(DEFAULT_PORT);
        nettyHttpServer.start();
    }

}
