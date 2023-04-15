package com.vv.server.config.impl;

import com.vv.server.config.ServiceConfig;

public class DefaultServiceConfig<T> implements ServiceConfig<T> {
    /**
     * 服务的唯一标识
     */
    private String id;

    /**
     * 设置引用类
     */
    private T reference;

    @Override
    public String id() {
        return id;
    }

    @Override
    public DefaultServiceConfig<T> id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public T reference() {
        return reference;
    }

    @Override
    public DefaultServiceConfig<T> reference(T reference) {
        this.reference = reference;
        return this;
    }
}
