package com.vv.client.handler;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcClientHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CalculateRequest request = new CalculateRequest(1, 2);

        ctx.writeAndFlush(request);
        log.info("[Client] request is :{}", request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        CalculateResponse response = (CalculateResponse)msg;
        log.info("[Client] response is :{}", response);
    }

}
