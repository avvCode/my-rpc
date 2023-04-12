package com.vv.server.encoder;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class CalculateRequestEncoder extends MessageToByteEncoder<CalculateResponse> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CalculateResponse calculateResponse, ByteBuf out) throws Exception {
        boolean success = calculateResponse.isSuccess();
        int result = calculateResponse.getSum();
        out.writeBoolean(success);
        out.writeInt(result);
    }
}
