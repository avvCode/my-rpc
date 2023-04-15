package com.vv.client.core.context;

import io.netty.channel.ChannelHandler;

/**
 * 客户端上下文信息
 * 什么是上下文信息？
 * 一个保存全局信息的对象
 */
public interface RpcClientContext {
    /**
     * 服务地址信息
     * @return 服务地址信息
     */
    String address();

    /**
     * 端口信息
     */
    int port();

    /**
     * 客户端处理 handler
     */
    ChannelHandler channelHandler();
}
