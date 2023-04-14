package com.vv.common.model.impl;

import com.vv.common.model.BaseRpc;
import com.vv.common.model.RpcRequest;

import java.util.Arrays;
import java.util.List;

public class DefaultRpcRequest implements RpcRequest {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 4284511516221766313L;
    /**
     * 唯一标识
     */
    String seqId;
    /**
     * 调用服务Id
     */
    String serviceId;
    /**
     * 请求创建时间
     */
    long createTime;
    /**
     * 方法名
     */
    String methodName;
    /**
     * 方法类型名字
     */
    List<String> paramTypeNames;
    /**
     * 方法参数值
     */
    Object[] paramValues;

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
    public String serviceId() {
        return serviceId;
    }

    @Override
    public long createTime() {
        return createTime;
    }
    public BaseRpc createTime(long createTime){
        this.createTime = createTime;
        return this;
    }
    @Override
    public String methodName() {
        return methodName;
    }
    public BaseRpc methodName(String methodName){
        this.methodName = methodName;
        return this;
    }
    @Override
    public List<String> paramTypeNames() {
        return paramTypeNames;
    }
    public BaseRpc paramTypeNames(List<String> paramTypeNames){
        this.paramTypeNames = paramTypeNames;
        return this;
    }
    @Override
    public Object[] paramValues() {
        return paramValues;
    }
    public BaseRpc paramValues(Object[] paramValues){
        this.paramValues = paramValues;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultRpcRequest{" +
                "seqId='" + seqId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", createTime=" + createTime +
                ", methodName='" + methodName + '\'' +
                ", paramTypeNames=" + paramTypeNames +
                ", paramValues=" + Arrays.toString(paramValues) +
                '}';
    }
}
