package com.example.manualrpc.rpc.service;

import com.example.manualrpc.rpc.RpcRequest;
import com.example.manualrpc.rpc.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class NettyRpcServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(NettyRpcServerHandler.class);

    private ConcurrentHashMap<String, ServiceConfig> serviceConfigMap =
            new ConcurrentHashMap<String, ServiceConfig>();

    public NettyRpcServerHandler(List<ServiceConfig> serviceConfigs) {
        for(ServiceConfig serviceConfig : serviceConfigs) {
            String serviceInterfaceClass = serviceConfig.getServiceInterfaceClass().getName();
            serviceConfigMap.put(serviceInterfaceClass, serviceConfig);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest rpcRequest = (RpcRequest)msg;
        logger.info("netty rpc server receives the request: " + rpcRequest);

        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());

        try {
            // 此时我们要实现一个什么东西呢？我们需要根据人家指定的class，获取到这个class
            // 根据人家的方法调用，去通过反射，构建这个class对象实例
            // 接着通过放射获取到这个class指定方法入参类型的method，反射调用，传入方法，拿到返回值
            ServiceConfig serviceConfig = serviceConfigMap.get(rpcRequest.getServiceInterfaceClass());
            Class clazz = serviceConfig.getServiceClass();
            Object instance = clazz.newInstance();
            Method method = clazz.getMethod(rpcRequest.getMethodName(),
                    rpcRequest.getParameterTypes());
            Object result = method.invoke(instance, rpcRequest.getArgs());

            // 把rpc调用结果封装到响应里去
            rpcResponse.setResult(result);
            rpcResponse.setSuccess(RpcResponse.SUCCESS);
        } catch(Exception e) {
            logger.error("netty rpc server failed to response the request.", e);
            rpcResponse.setSuccess(RpcResponse.FAILURE);
            rpcResponse.setException(e);
        }

        ctx.write(rpcResponse);
        ctx.flush();
        logger.info("send rpc response to client: " + rpcResponse);
    }
}
