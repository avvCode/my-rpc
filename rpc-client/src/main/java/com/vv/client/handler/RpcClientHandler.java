package com.vv.client.handler;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcClientHandler extends SimpleChannelInboundHandler {
    private CalculateResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        CalculateResponse response = (CalculateResponse)msg;
        this.response = response;
        log.info("[Client] response is :{}", response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 每次用完要关闭，不然拿不到response，我也不知道为啥（目测得了解netty才行）
        // 个人理解：如果不关闭，则永远会被阻塞。
        ctx.flush();
        ctx.close();
    }

    public  CalculateResponse getResponse(){
        return response;
    }
}
