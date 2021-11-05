package com.eleven.icode.malluser.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author by YingLong on 2021/11/5
 */
@Component
public class FallbackOrderFeignServiceFactory implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable throwable) {
        return new OrderFeignService() {
            @Override
            public Object findOrderByUserId(Integer userId) {
                return "=======服务降级了========";
            }
        };
    }
}
