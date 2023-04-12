package com.vv.client.core;

import com.vv.client.decoder.CalculateResponseDecoder;
import com.vv.client.encoder.CalculateRequestEncoder;
import com.vv.client.handler.RpcClientHandler;
import com.vv.common.constant.RpcConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcClient extends Thread{
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
        log.info("RPC 服务开始启动客户端");

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<Channel>(){
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
//                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new CalculateRequestEncoder())
                                    .addLast(new CalculateResponseDecoder())
                                    .addLast(new RpcClientHandler());
                        }
                    })
                    .connect("localhost", connectPort)
                    .syncUninterruptibly();

            log.info("RPC 服务启动客户端完成，监听端口：" + connectPort);
            channelFuture.channel().closeFuture().syncUninterruptibly();
            log.info("RPC 服务开始客户端已关闭");
        } catch (Exception e) {
            log.error("RPC 客户端遇到异常", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new RpcClient(9527).start();
    }
}
