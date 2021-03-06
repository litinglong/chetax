//package com.silva.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import reactor.core.publisher.Mono;
//
///**
// * @author Hpsyche
// */
////@Component
//public class TokenFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest req = exchange.getRequest().mutate()
//                .header("from", "gateway").build();
//        return chain.filter(exchange.mutate().request(req.mutate().build()).build());
//    }
//    @Override
//    public int getOrder() {
//        return -100;
//    }
//}
