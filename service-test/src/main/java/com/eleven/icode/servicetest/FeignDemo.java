package com.eleven.icode.servicetest;

import com.eleven.icode.servicetest.service.RemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * @author by YingLong on 2021/10/18
 */
public class FeignDemo {

    public static void main(String[] args) {
        RemoteService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://localhost:8020/");
        System.out.println(service.findOrderByUserId(2));
    }
}
