package com.eleven.icode.malluser.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.eleven.icode.malluser.util.GlobalExceptionHandler;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/11/5
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            blockHandler = "handleException", blockHandlerClass = GlobalExceptionHandler.class,
            fallback = "fallback", fallbackClass = GlobalExceptionHandler.class
    )
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
