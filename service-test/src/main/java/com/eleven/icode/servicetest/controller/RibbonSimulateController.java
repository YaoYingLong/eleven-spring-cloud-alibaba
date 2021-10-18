package com.eleven.icode.servicetest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author by YingLong on 2021/10/18
 */
@Slf4j
@RestController
@RequestMapping(value = "test")
public class RibbonSimulateController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public Object findOrderByUserId(@PathVariable("id") Integer id) {
        // RestTemplate调用
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        //模拟ribbon实现
        String url = getUri("mall-order") + "/order/findOrderByUserId/" + id;
        // 添加@LoadBalanced
        //String url = "http://mall-order/order/findOrderByUserId/"+id;
        log.info("url:" + url);
        Object result = restTemplate.getForObject(url, Object.class);
        return result;
    }

    private String getUri(String serviceName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if (serviceInstances == null || serviceInstances.isEmpty()) {
            return null;
        }
        int serviceSize = serviceInstances.size();
        int indexServer = incrementAndGetModel(serviceSize);
        return serviceInstances.get(indexServer).getUri().toString();
    }

    private AtomicInteger nextIndex = new AtomicInteger();
    private int incrementAndGetModel(int serviceSize) {
        for (; ; ) {
            int current = nextIndex.get();
            int next = (current + 1) % serviceSize;
            if (nextIndex.compareAndSet(current, next) && current < serviceSize) {
                return current;
            }
        }

    }


}
