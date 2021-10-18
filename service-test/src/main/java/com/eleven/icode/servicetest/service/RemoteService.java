package com.eleven.icode.servicetest.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author by YingLong on 2021/10/18
 */
public interface RemoteService {

    @Headers(value = {"Content-Type: application/json", "Accept: application/json"})
    @RequestLine(value = "GET /order/findOrderByUserId/{userId}")
    Object findOrderByUserId(@Param(value = "userId") Integer userId);

}
