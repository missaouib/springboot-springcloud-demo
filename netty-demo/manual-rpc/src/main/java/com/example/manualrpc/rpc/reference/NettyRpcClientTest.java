package com.example.manualrpc.rpc.reference;

import com.example.manualrpc.rpc.service.TestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyRpcClientTest {

    private static final Logger logger = LogManager.getLogger(NettyRpcClientTest.class);

    public static void main(String[] args) {
        ReferenceConfig referenceConfig = new ReferenceConfig(TestService.class);
        TestService testService = (TestService) RpcServiceProxy.createProxy(referenceConfig);
        String result = testService.sayHello("zhangsan");
        logger.info("rpc call finished: " + result);
    }

}
