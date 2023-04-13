package com.vv.client.core;

import com.vv.client.decoder.CalculateResponseDecoder;
import com.vv.client.encoder.CalculateRequestEncoder;
import com.vv.client.handler.RpcClientHandler;
import com.vv.common.constant.RpcConstant;
import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
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
//                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new CalculateRequestEncoder())
                                    .addLast(new CalculateResponseDecoder())
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
        channel.writeAndFlush(request);
        channel.closeFuture().syncUninterruptibly();

        return channelHandler.getResponse();
    }
}
