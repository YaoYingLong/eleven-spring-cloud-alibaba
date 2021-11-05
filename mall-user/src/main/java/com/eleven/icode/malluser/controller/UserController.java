package com.eleven.icode.malluser.controller;

import com.eleven.icode.malluser.client.OrderFeignService;
import com.eleven.icode.malluser.config.ExtAddrConf;
import com.eleven.icode.malluser.config.ThreadPoolConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @Value(value = "${test.main.name: aa}")
    private String addr;
    //    @Autowired
//    private ExtAddrConf conf;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ThreadPoolConf threadPoolConf;
    @Autowired
    private OrderFeignService orderFeignService;

    @RequestMapping(value = "findOrderByUserId/{userId}")
//    @SentinelResource(value = "getUser", fallback = "fallback", fallbackClass = ExceptionUtil.class,
//            blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public Object getUser(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        String url = "http://mall-order/order/findOrderByUserId/" + userId;
        Object result = restTemplate.getForObject(url, Object.class);
        return result;
    }

    @RequestMapping(value = "findOrderByUserIdV2/{userId}")
//    @SentinelResource(value = "getUser", fallback = "fallback", fallbackClass = ExceptionUtil.class,
//            blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public Object getUserV2(@PathVariable(value = "userId") Integer userId) {
        log.info("根据userId:" + userId + "查询订单信息");
        Object result = orderFeignService.findOrderByUserId(userId);
        return result;
    }

    @RequestMapping(value = "value")
    public String value() {
        return addr;
    }

    @RequestMapping(value = "conf")
    public ExtAddrConf conf() {
        return null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserController init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserController destroy");
    }

    @RequestMapping(value = "getConf")
    private ThreadPoolConf getConf() {
        return threadPoolConf;
    }
}
