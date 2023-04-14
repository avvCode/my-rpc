package com.vv.client.proxy;

import com.vv.client.core.RpcClient;
import com.vv.common.domain.CalculateRequest;
import com.vv.common.domain.CalculateResponse;
import com.vv.common.service.Calculator;

public class CalculatorProxy implements Calculator {
    /**
     * rpc 客户端
     */
    private RpcClient rpcClient;

    /**
     * 创建类
     * （1）默认初始化 client 端
     */
    public CalculatorProxy() {
        rpcClient = new RpcClient();
        rpcClient.start();
    }

    @Override
    public CalculateResponse sum(CalculateRequest request) {
        return rpcClient.calculate(request);
    }
}
