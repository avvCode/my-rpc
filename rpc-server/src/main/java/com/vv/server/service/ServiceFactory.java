package com.vv.server.service;

import com.vv.server.config.ServiceConfig;

import java.util.List;

/**
 * 服务方法仓库存储
 * 相当于一个存储实体对象的类
 */
public interface ServiceFactory {

    /**
     * 注册服务列表信息 (注册说白了就是保存一下方法信息)
     * @param serviceConfigList 服务配置列表 (id - 方法信息)
     * @return this
     */
    ServiceFactory registerServices(final List<ServiceConfig> serviceConfigList);

    /**
     * 直接反射调用
     * （1）此处对于方法反射，为了提升性能，所有的 class.getFullName() 进行拼接然后放进 key 中。
     *
     * @param serviceId 服务名称
     * @param methodName 方法名称
     * @param paramTypeNames 参数类型名称列表
     * @param paramValues 参数值
     * @return 方法调用返回值
     */
    Object invoke(final String serviceId, final String methodName,
                  List<String> paramTypeNames, final Object[] paramValues);
}
