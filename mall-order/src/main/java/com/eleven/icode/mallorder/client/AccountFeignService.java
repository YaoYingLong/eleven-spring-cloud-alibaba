package com.eleven.icode.mallorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fox
 */
@FeignClient(name = "mall-user", path = "/account")
public interface AccountFeignService {

    @RequestMapping(value = "/debit")
    Boolean debit(@RequestParam(value = "userId") String userId,
                  @RequestParam(value = "money") int money);
}
