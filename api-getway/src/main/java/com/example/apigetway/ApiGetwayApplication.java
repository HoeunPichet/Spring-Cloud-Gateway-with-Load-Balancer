package com.example.apigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

@SpringBootApplication
//@EnableEurekaClient
// Optional: Only if you want to avoid pulling in Eureka beans
//@LoadBalancerClients
public class ApiGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetwayApplication.class, args);
    }
//    @Component
//    public class LoggingFilter implements GlobalFilter {
//        private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
//
//        @Override
//        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//            URI requestUri = exchange.getRequest().getURI();
//            log.info("Routing to: {}", requestUri);
//            return chain.filter(exchange);
//        }
//    }
}
