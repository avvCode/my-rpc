package com.vv.common.support.time.impl;

import com.vv.common.support.time.Time;

public class TimeUtil implements Time {
    private TimeUtil(){}
    @Override
    public long time() {
        return System.currentTimeMillis();
    }
}
