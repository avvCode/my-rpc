package com.vv.client.core;

import com.github.houbb.json.bs.JsonBs;
import com.vv.client.handler.RpcClientHandler;
import com.vv.common.constant.RpcConstant;
import com.vv.common.domain.CalculateRequest;
import com.vv.common.domain.CalculateResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcClient extends Thread{

    private RpcClientHandler channelHandler;

    private ChannelFuture channelFuture;

    private final int connectPort;

    public RpcClient(int connectPort){
        this.connectPort = connectPort;
    }

    public RpcClient(){
        this(RpcConstant.PORT);
    }

    @Override
    public void run() {
        // 启动服务端
        log.info("RPC客户端 开始启动");
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            channelFuture = bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<Channel>(){
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            channelHandler = new RpcClientHandler();
                            ch.pipeline()
                                    // 解码 bytes=>resp
                                    .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    // 编码 request=>bytes
                                    .addLast(new ObjectEncoder())
                                    .addLast(channelHandler);
                        }
                    })
                    .connect(RpcConstant.ADDRESS, connectPort)
                    .syncUninterruptibly();
            log.info("RPC 启动客户端完成，监听端口：" + connectPort);
        } catch (Exception e) {
            log.error("RPC 客户端遇到异常", e);
        }
    }

    /**
     * 调用计算
     * @param request 请求信息
     * @return 结果
     * @since 0.0.4
     */
    public CalculateResponse calculate(final CalculateRequest request) {
        // 发送请求
        final Channel channel = channelFuture.channel();
        log.info("RPC 客户端发送请求，request: {}", request);

        // 关闭当前线程，以获取对应的信息
        //转换字节数组
        byte[] requestBytes = JsonBs.serializeBytes(request);
        ByteBuf requestByteBuf = Unpooled.copiedBuffer(requestBytes);
        channel.writeAndFlush(requestByteBuf);
        channel.closeFuture().syncUninterruptibly();

        return channelHandler.getResponse();
    }
}
