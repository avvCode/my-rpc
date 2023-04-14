package com.vv.common.domain;

import java.io.Serializable;

/**
 * 通用请求响应类的序列化
 */
public interface BaseRpc extends Serializable {
    /**
     * 获取唯一标识号
     * （1）用来唯一标识一次调用，便于获取该调用对应的响应信息。
     * @return 唯一标识号
     */
    String seqId();

    /**
     * 设置唯一标识号
     * @param traceId 唯一标识号
     * @return this
     */
    BaseRpc seqId(final String traceId);
}
