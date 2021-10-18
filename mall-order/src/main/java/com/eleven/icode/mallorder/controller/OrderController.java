package com.eleven.icode.mallorder.controller;

import com.eleven.icode.mallorder.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by YingLong on 2021/10/18
 */
@Slf4j
@RestController
@RequestMapping(value = "order")
public class OrderController {

    @RequestMapping(value = "findOrderByUserId/{userId}")
    public Order findOrderByUserId(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        return null;
    }

}
