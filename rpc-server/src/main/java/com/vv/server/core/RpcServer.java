package com.vv.server.core;



import com.vv.common.constant.RpcConstant;
import com.vv.server.decoder.CalculateResponseDecoder;
import com.vv.server.encoder.CalculateRequestEncoder;
import com.vv.server.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServer extends Thread{

    private final int port;

    public RpcServer(int port){
        this.port = port;
    }

    public RpcServer(){
        this(RpcConstant.PORT);
    }

    @Override
    public void run() {
        log.info("RPC 服务启动...");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(workerGroup,bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline()
//                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new CalculateResponseDecoder())
                                    .addLast(new CalculateRequestEncoder())
                                    .addLast(new RpcServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture channelFuture = serverBootstrap.bind(port).syncUninterruptibly();
            log.info("RPC 服务端启动完成，监听【" + port + "】端口");
            channelFuture.channel().closeFuture().syncUninterruptibly();
            log.info("RPC 服务端关闭完成");
        } catch (Exception e) {
            log.error("RPC 服务异常", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
