package com.vv.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateRequest implements Serializable {

    private static final long serialVersionUID = 6420751004355300996L;

    /**
     * 参数一
     */
    private int one;

    /**
     * 参数二
     */
    private int two;

    @Override
    public String toString() {
        return "CalculateRequest{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}