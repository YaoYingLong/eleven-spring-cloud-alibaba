package com.eleven.icode.malluser;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.eleven.icode.malluser.util.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
@EnableFeignClients
@ConfigurationPropertiesScan
@SpringBootApplication
public class MallUserAppliction {

    public static void main(String[] args) {
        SpringApplication.run(MallUserAppliction.class, args);
    }

//    @Bean
//    public IRule ribbonRule() {
//        return new NacosRule();
//    }
}
