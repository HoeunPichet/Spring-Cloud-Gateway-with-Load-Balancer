package com.example.apigetway.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)  // Lower value = higher priority (executed earlier)
public class LoggingGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Pre-processing (before request is forwarded)
        System.out.println("Global Pre Filter: Request Path -> " + exchange.getRequest().getPath());

        // Continue filter chain
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Post-processing (after response is received)
            System.out.println("Global Post Filter: Response Status -> " + exchange.getResponse().getStatusCode());
        }));
    }
}