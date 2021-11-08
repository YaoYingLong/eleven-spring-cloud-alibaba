package com.eleven.icode.mallorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fox
 */
@FeignClient(name = "mall-storage", path = "/storage")
public interface StorageFeignService {

    @RequestMapping(path = "/deduct")
    Boolean deduct(@RequestParam(value = "commodityCode") String commodityCode,
                          @RequestParam(value = "count") Integer count);

}
