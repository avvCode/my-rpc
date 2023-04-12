package com.vv.server.decoder;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * 响应解码器
 */
public class CalculateResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int one = in.readInt();
        int two = in.readInt();

        CalculateRequest request = new CalculateRequest(one, two);
        out.add(request);
    }
}
