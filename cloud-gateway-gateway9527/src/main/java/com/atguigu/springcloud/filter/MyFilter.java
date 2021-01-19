package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/16 21:44
 */
@Component
public class MyFilter implements GlobalFilter, Ordered {

    /**
     *  自定义过滤器，加了@Component之后访问9527网关下路的路径需要先过这个过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("**********com in MyLogGateWayFilter:" + LocalDate.now());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null) {
            System.out.println("**********用户名为null,非法用户o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //该过滤器执行的顺序 数字越小优先级越高
        return 0;
    }
}
