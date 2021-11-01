package com.eleven.icode.malluser;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.eleven.icode.malluser.config.ThreadPoolConf;
import com.eleven.icode.malluser.handler.GlobalExceptionHandler;
import com.eleven.icode.malluser.util.GlobalExceptionUtil;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class MallUserAppliction {

    public static void main(String[] args) {
        SpringApplication.run(MallUserAppliction.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            blockHandler = "handleException",blockHandlerClass = GlobalExceptionUtil.class,
            fallback = "fallback",fallbackClass = GlobalExceptionUtil.class
    )
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public IRule ribbonRule() {
//        return new NacosRule();
//    }
}
