package com.vv.client.config.reference;

/**
 * 引用配置类
 */
public interface ReferenceConfig<T> {
    /**
     * 设置服务标识
     * @param serviceId 服务标识
     * @return this
     */
    ReferenceConfig<T> serviceId(final String serviceId);

    /**
     * 服务唯一标识
     */
    String serviceId();

    /**
     * 服务接口
     * @return 接口信息
     */
    Class<T> serviceInterface();

    /**
     * 设置服务接口信息
     * @param serviceInterface 服务接口信息
     * @return this
     */
    ReferenceConfig<T> serviceInterface(final Class<T> serviceInterface);

    /**
     * 设置服务地址信息
     * （1）单个写法：ip:port:weight
     * （2）集群写法：ip1:port1:weight1,ip2:port2:weight2
     *
     * 其中 weight 权重可以不写，默认为1.
     *
     * @param addresses 地址列表信息
     * @return this
     */
    ReferenceConfig<T> addresses(final String addresses);

    /**
     * 获取对应的引用实现
     * @return 引用代理类
     */
    T reference();
}
