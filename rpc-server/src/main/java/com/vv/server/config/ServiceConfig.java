package com.vv.server.config;

public interface ServiceConfig<T> {
    /**
     * 获取唯一标识
     * @return 获取唯一标识
     */
    String id();

    /**
     * 设置唯一标识
     * @param id 标识信息
     * @return this
     */
    ServiceConfig<T> id(String id);

    /**
     * 获取引用实体实现
     * @return 实体实现
     */
    T reference();

    /**
     * 设置引用实体实现
     * @param reference 引用实现
     * @return this
     */
    ServiceConfig<T> reference(T reference);
}
