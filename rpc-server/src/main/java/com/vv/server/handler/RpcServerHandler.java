package com.vv.server.handler;

import com.github.houbb.json.bs.JsonBs;
import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import com.vv.common.service.Calculator;
import com.vv.server.service.CalculatorService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final String id = ctx.channel().id().asLongText();
        log.info("[server] channel {} connected",id);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String id = ctx.channel().id().asLongText();
        //接收到request
        ByteBuf requestByteBuf = (ByteBuf) msg;
        int length = requestByteBuf.readableBytes();
        byte[] requestBytes = new byte[length];
        requestByteBuf.readBytes(requestBytes);
        CalculateRequest request = JsonBs.deserializeBytes(requestBytes, CalculateRequest.class);
        log.info("[server] receive from channel: {} request {}",id,request);
        //调用本地实现
        CalculatorService calculatorService = new CalculatorService();
        CalculateResponse response = calculatorService.sum(request);
        //实现后将结果返回客户端
        byte[] responseBytes = JsonBs.serializeBytes(response);
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(responseBytes);
        ctx.writeAndFlush(responseByteBuf);
        log.info("[Server] channel {} response {}", id, response);
    }
}
