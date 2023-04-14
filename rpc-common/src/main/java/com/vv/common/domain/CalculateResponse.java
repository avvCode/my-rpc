package com.vv.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResponse implements Serializable {

    private static final long serialVersionUID = -1972014736222511341L;
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 二者的和
     */
    private int sum;

    @Override
    public String toString() {
        return "CalculateResponse{" +
                "success=" + success +
                ", sum=" + sum +
                '}';
    }
}