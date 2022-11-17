package com.example.manualrpc;

import com.example.manualrpc.rpc.HessianSerialization;
import com.example.manualrpc.rpc.RpcRequest;

import java.util.UUID;

public class HessianSerializationTest {

    public static void main(String[] args) throws Exception {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(UUID.randomUUID().toString()
                .replace("-", ""));
        rpcRequest.setServiceInterfaceClass("TestClass");
        rpcRequest.setMethodName("sayHello");
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setArgs(new Object[]{"zhangsan"});

        byte[] bytes = HessianSerialization.serialize(rpcRequest);
        System.out.println(bytes.length);

        RpcRequest deserializedRpcRequest =
                (RpcRequest) HessianSerialization.deserialize(bytes, RpcRequest.class);
        System.out.println(deserializedRpcRequest);
    }

}
