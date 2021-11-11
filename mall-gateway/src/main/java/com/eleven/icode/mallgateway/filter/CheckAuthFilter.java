package com.eleven.icode.mallgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Fox
 */
@Component
@Order(-1)
@Slf4j
public class CheckAuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //校验请求头中的token
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if ("127.0.0.1".equals(getIp(headers))) {
            List<String> token = exchange.getRequest().getHeaders().get("token");
            log.info("token:" + token);
            if (token.isEmpty()) {
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }

    private String getIp(HttpHeaders headers) {
        return headers.getHost().getHostName();
    }
}
