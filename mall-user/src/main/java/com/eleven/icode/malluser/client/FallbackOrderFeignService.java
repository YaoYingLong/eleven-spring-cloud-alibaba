package com.eleven.icode.malluser.client;

import org.springframework.stereotype.Component;

/**
 * @author by YingLong on 2021/11/5
 */
@Component   //必须交给spring 管理
public class FallbackOrderFeignService implements OrderFeignService {
    @Override
    public Object findOrderByUserId(Integer userId) {
        return "=======服务降级了========";
    }
}
