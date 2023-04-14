package com.vv.common.model;

/**
 * 基础响应格式
 */
public interface RpcResponse extends BaseRpc{
    /**
     * 异常
     */
    Throwable error();
    /**
     * 结果
     */
    Object result();
}
