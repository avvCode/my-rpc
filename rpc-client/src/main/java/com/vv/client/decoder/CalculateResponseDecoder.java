package com.vv.client.decoder;

import com.vv.common.model.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * 响应解码器
 */
public class CalculateResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        boolean success = in.readBoolean();
        int sum = in.readInt();

        CalculateResponse response = new CalculateResponse(success, sum);
        out.add(response);
    }
}
