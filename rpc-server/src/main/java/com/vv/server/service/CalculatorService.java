package com.vv.server.service;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;
import com.vv.common.service.Calculator;

/**
 * 测试计算类
 */
public class CalculatorService implements Calculator {

    @Override
    public CalculateResponse sum(CalculateRequest request) {
        int sum = request.getOne()+request.getTwo();

        return new CalculateResponse(true, sum);
    }

}
