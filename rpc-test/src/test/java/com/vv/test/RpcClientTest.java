package com.vv.test;

import com.vv.client.core.RpcClient;
import com.vv.client.proxy.CalculatorProxy;
import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import com.vv.common.service.Calculator;


public class RpcClientTest {
    public static void main(String[] args) throws InterruptedException {
        Calculator calculator = new CalculatorProxy();

        CalculateRequest request = new CalculateRequest();

        request.setOne(5);
        request.setTwo(89);
        //等待客户端启动完成，再去调用方法
        //否则异步会导致先发请求再启动客户端出现NPE
        Thread.sleep(1000*5);
        CalculateResponse response = calculator.sum(request);
        System.out.println("rpc call result: " + response);
    }
}
