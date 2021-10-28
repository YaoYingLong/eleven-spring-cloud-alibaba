package com.eleven.icode.malluser.controller;

import com.eleven.icode.malluser.conf.ThreadPoolConf;
import com.eleven.icode.malluser.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ThreadPoolConf threadPoolConf;

    @RequestMapping(value = "findOrderByUserId/{userId}")
    public User getUser(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        String url = "http://mall-order/order/findOrderByUserId/" + userId;
        Object result = restTemplate.getForObject(url, Object.class);
        return null;
    }

    @RequestMapping(value = "getConf")
    private ThreadPoolConf getConf() {
        return threadPoolConf;
    }

}
