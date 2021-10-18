package com.eleven.icode.servicetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author by YingLong on 2021/10/18
 */
@SpringBootApplication
public class ServiceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
