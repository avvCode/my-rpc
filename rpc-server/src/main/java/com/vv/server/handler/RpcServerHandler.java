package com.vv.server.handler;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import com.vv.common.service.Calculator;
import com.vv.server.service.CalculatorService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        final String id = channelHandlerContext.channel().id().asLongText();
        log.info("[server] channel {} connected",id);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String id = ctx.channel().id().asLongText();
        CalculateRequest request = (CalculateRequest) msg;
        log.info("[server] receive from channel: {} request {}",id,request);
        CalculatorService calculatorService = new CalculatorService();
        CalculateResponse response = calculatorService.sum(request);
        ctx.writeAndFlush(response);
        log.info("[Server] channel {} response {}", id, response);
    }
}
