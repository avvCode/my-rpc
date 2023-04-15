package com.vv.server.service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 在服务中的方法
 * 相当于一个实体类，对应一个服务端方法
 */
public interface ServiceMethod {
    /**
     * 方法名字
     * @return 方法名字
     */
    String methodName();

    /**
     * 方法参数列表类型
     * @return 方法参数列表类型
     */
    Class[] paramTypes();

    /**
     * 方法信息
     * @return 方法信息
     */
    Method method();

    /**
     * 方法参数列表 真实值
     * @return 方法参数列表 真实值
     */
    List<String> paramNames();
}
