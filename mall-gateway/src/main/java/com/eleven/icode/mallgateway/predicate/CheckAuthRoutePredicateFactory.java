package com.eleven.icode.mallgateway.predicate;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author by YingLong on 2021/11/10
 */
@Log4j2
//@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (GatewayPredicate) serverWebExchange -> {
            log.info("调用CheckAuthRoutePredicateFactory" + config.getName());
            if("eleven".equals(config.getName())){
                return true;
            }
            return false;
        };
    }

    /**
     * 快捷配置
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("name");
    }

    /**
     * 需要定义一个内部类，该类用于封装application.yml中的配置
     */
    public static class Config {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
