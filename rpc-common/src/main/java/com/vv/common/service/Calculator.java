package com.vv.common.service;

import com.vv.common.model.CalculateRequest;
import com.vv.common.model.CalculateResponse;

public interface Calculator {

    /**
     * 计算加法
     * @param request 请求入参
     * @return 返回结果
     */
    CalculateResponse sum(final CalculateRequest request);

}
