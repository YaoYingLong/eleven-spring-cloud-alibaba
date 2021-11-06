package com.eleven.icode.mallorder.entity;

import lombok.Data;

/**
 * @author by YingLong on 2021/11/1
 */
@Data
public class ElevenResponse {
    private Integer code;
    private String msg;

    public ElevenResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
