package com.vv.common.support.id.impl;

import com.vv.common.support.id.Id;

import java.util.UUID;

public class CodeUtil implements Id {
    public String id() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
