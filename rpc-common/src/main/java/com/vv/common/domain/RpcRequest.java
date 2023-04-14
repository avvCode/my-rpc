package com.vv.common.domain;

import java.util.List;

/**
 * 基础请求格式
 * 一个方法的所有信息
 */
public interface RpcRequest extends BaseRpc{
    /**
     * 获取服务的唯一标识
     * @return 服务唯一标识
     */
    String serviceId();

    /**
     * 本次请求创建时间
     * @return 创建时间
     */
    long createTime();

    /**
     * 被调用的方法名称
     * @return 调用方法名字
     */
    String methodName();

    /**
     * 被调用方法的各个参数的类型名称
     * @return 方法各个参数的 类型名称
     */
    List<String> paramTypeNames();

    /**
     * 被调用方法的各个参数的值
     * @return 方法各个参数的 值
     */
    Object [] paramValues();
}
