package com.eleven.icode.mallgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Fox
 */
@Component
@Slf4j
public class CheckIPFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        //模拟对 IP 的访问限制，即不在 IP 白名单中就不能调用的需求
        if (getIp(headers).equals("127.0.0.1")) {
            log.info("======非法访问======");
            ServerHttpResponse response = exchange.getResponse();
            byte[] bytes = new String("======非法访问======").getBytes();
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    private String getIp(HttpHeaders headers) {
        return headers.getHost().getHostName();
    }
}
