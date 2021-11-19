package com.eleven.icode.elevenredis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author by YingLong on 2021/11/16
 */
@SpringBootApplication
public class ElevenRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElevenRedisApplication.class, args);
    }


    @Bean
    public Redisson redisson() { // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
