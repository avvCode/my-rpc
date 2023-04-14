package com.vv.common.domain.impl;

import com.vv.common.domain.BaseRpc;
import com.vv.common.domain.RpcResponse;

public class DefaultRpcResponse implements RpcResponse {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -2195142882293576847L;
    /**
     * 唯一标识
     */
    String seqId;
    /**
     * 调用方法出错时异常
     */
    Throwable error;
    /**
     * 调用方法成功时返回的结果
     */
    Object result;

    @Override
    public String seqId() {
        return seqId;
    }

    @Override
    public BaseRpc seqId(String seqId) {
        this.seqId = seqId;
        return this;
    }

    @Override
    public Throwable error() {
        return error;
    }

    public BaseRpc error(Throwable error){
        this.error = error;
        return this;
    }

    @Override
    public Object result() {
        return result;
    }

    public BaseRpc result(Object result){
        this.result = result;
        return this;
    }
    @Override
    public String toString() {
        return "DefaultRpcResponse{" +
                "seqId='" + seqId + '\'' +
                ", error=" + error +
                ", result=" + result +
                '}';
    }
}
