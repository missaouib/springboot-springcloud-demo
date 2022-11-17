package com.example.manualrpc.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private static final int MESSAGE_LENGTH_BYTES = 4;
    private static final int MESSAGE_LENGTH_VALID_MINIMUM_VALUE = 0;

    private Class<?> targetClass;

    public RpcDecoder(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 校验消息长度的字节数，必须达到4个字节，此时才可以继续往下走
        if(byteBuf.readableBytes() < MESSAGE_LENGTH_BYTES) {
            return;
        }

        // 对于byteBuf当前可以读的readerIndex，mark标记
        // 后续我可以通过这个mark标记，我可以找回来在发起read读取之前的一个readerIndex位置
        byteBuf.markReaderIndex();

        // 读取4个字节的int，int代表了你的消息bytes长度
        int messageLength = byteBuf.readInt();
        // 如果说此时消息长度是小于0，说明此时通信已经出现了故障
        if(messageLength < MESSAGE_LENGTH_VALID_MINIMUM_VALUE) {
            channelHandlerContext.close();
        }

        // 此时消息字节数据没有接收完整，可以读的字节数是比消息字节长度是小的
        // 检查经典的拆包问题
        if(byteBuf.readableBytes() < messageLength) {
            byteBuf.resetReaderIndex();
            return;
        }

        // 反序列化
        byte[] bytes = new byte[messageLength];
        byteBuf.readBytes(bytes);
        Object object = HessianSerialization.deserialize(bytes, targetClass);
        list.add(object);
    }

}
