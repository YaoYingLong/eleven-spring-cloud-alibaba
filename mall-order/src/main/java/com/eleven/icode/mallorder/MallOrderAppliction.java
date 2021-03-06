package com.eleven.icode.mallorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author by YingLong on 2021/10/18
 */
@SpringBootApplication
@EnableFeignClients
public class MallOrderAppliction {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderAppliction.class, args);
    }

}
