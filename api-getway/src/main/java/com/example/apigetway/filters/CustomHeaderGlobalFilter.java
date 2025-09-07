package com.example.apigetway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
@Order(0)  // Runs after LoggingGlobalFilter
public class CustomHeaderGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Add header before forwarding
        exchange.getRequest().mutate()
                .header("X-Global-Request", "FromGateway")
                .build();
        System.out.println("Global Test");

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Add response header before sending back
            exchange.getResponse()
                    .getHeaders()
                    .add("X-Global-Response", "ProcessedByGateway");
        }));
    }
}
