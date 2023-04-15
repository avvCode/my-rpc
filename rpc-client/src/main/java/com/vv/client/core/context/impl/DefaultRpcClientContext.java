package com.vv.client.core.context.impl;

import com.vv.client.core.context.RpcClientContext;
import io.netty.channel.ChannelHandler;

public class DefaultRpcClientContext implements RpcClientContext {
    /**
     * 服务地址信息
     */
    private String address;

    /**
     * 端口信息
     */
    private int port;

    /**
     * 客户端处理 handler
     */
    private ChannelHandler channelHandler;

    @Override
    public String address() {
        return address;
    }

    public DefaultRpcClientContext address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public int port() {
        return port;
    }

    public DefaultRpcClientContext port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public ChannelHandler channelHandler() {
        return channelHandler;
    }

    public DefaultRpcClientContext channelHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        return this;
    }
}
