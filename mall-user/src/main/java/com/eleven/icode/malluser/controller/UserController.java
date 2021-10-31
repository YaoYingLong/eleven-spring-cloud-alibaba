package com.eleven.icode.malluser.controller;

import com.eleven.icode.malluser.config.ExtAddrConf;
import com.eleven.icode.malluser.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@RequestMapping(value = "/user")
public class UserController implements InitializingBean, DisposableBean {


    @Value(value = "${test.main.name}")
    private String addr;
    @Autowired
    private ExtAddrConf conf;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "findOrderByUserId/{userId}")
    public User getUser(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        String url = "http://mall-order/order/findOrderByUserId/" + userId;
        Object result = restTemplate.getForObject(url, Object.class);
        return null;
    }

    @RequestMapping(value = "value")
    public String value() {
        return addr;
    }

    @RequestMapping(value = "conf")
    public ExtAddrConf conf() {
        return conf;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserController init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserController destroy");
    }
}
