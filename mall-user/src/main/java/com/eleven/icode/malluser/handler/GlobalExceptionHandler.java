package com.eleven.icode.malluser.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author by YingLong on 2021/11/1
 */
public class GlobalExceptionHandler {
    public static String fallback(Integer id, Throwable e) {
        return "===被异常降级啦===" + id;
    }

    public static String handleException(Integer id, BlockException e) {
        return "===被限流啦===" + id;
    }
}
