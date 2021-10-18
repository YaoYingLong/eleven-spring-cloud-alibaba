package com.eleven.icode.malluser;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
@SpringBootApplication
public class MallUserAppliction {

    public static void main(String[] args) {
        SpringApplication.run(MallUserAppliction.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public IRule ribbonRule() {
//        return new NacosRule();
//    }
}
