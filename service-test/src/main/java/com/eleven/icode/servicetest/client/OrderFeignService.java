package com.eleven.icode.servicetest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by YingLong on 2021/11/5
 */
@FeignClient(value = "mall-order", path = "/order", fallback = FallbackOrderFeignService.class)
public interface OrderFeignService {

    @RequestMapping(value = "/findOrderByUserId/{userId}")
    Object findOrderByUserId(@PathVariable(value = "userId") Integer userId);

}
