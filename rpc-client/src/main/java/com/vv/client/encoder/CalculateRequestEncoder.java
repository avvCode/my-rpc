package com.vv.client.encoder;

import com.vv.common.model.CalculateRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class CalculateRequestEncoder extends MessageToByteEncoder<CalculateRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CalculateRequest calculateRequest, ByteBuf out) throws Exception {
        int one = calculateRequest.getOne();
        int two = calculateRequest.getTwo();

        out.writeInt(one);
        out.writeInt(two);
    }
}
