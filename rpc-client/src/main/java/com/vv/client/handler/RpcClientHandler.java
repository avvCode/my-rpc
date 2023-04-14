package com.vv.client.handler;

import com.github.houbb.json.bs.JsonBs;
import com.vv.common.domain.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcClientHandler extends SimpleChannelInboundHandler {
    private CalculateResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将读取到的消息转换为ByteBuf
        ByteBuf buf = (ByteBuf) msg;
        //获取服务端穿回来的可读字节长度
        int length = buf.readableBytes();
        //将可读字节写入一个字节数组
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        //将字节转换成为对象
        this.response = JsonBs.deserializeBytes(bytes, CalculateResponse.class);
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
