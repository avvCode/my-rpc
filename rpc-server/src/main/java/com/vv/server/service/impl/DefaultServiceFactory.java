package com.vv.server.service.impl;

import com.vv.server.config.ServiceConfig;
import com.vv.server.service.ServiceFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DefaultServiceFactory implements ServiceFactory {
    /**
     * 服务 map
     * @since 0.0.6
     */
    private Map<String, Object> serviceMap;

    /**
     * 直接获取对应的 method 信息
     * （1）key: serviceId:methodName:param1@param2@param3
     * （2）value: 对应的 method 信息
     */
    private Map<String, Method> methodMap;

    private static final DefaultServiceFactory INSTANCE = new DefaultServiceFactory();

    private DefaultServiceFactory(){}

    public static DefaultServiceFactory getInstance() {
        return INSTANCE;
    }
    @Override
    public ServiceFactory registerServices(List<ServiceConfig> serviceConfigList) {
        return null;
    }

    @Override
    public Object invoke(String serviceId, String methodName, List<String> paramTypeNames, Object[] paramValues) {
        return null;
    }
}
