package com.eleven.icode.malluser;

import com.eleven.icode.malluser.conf.ThreadPoolConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
//@ConfigurationPropertiesScan
@EnableConfigurationProperties(value = ThreadPoolConf.class)
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
