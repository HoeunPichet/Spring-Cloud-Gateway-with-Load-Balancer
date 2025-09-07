package com.example.apigetway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayRoutesConfig {

//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service-route", r -> r
                        .path("/api/users/**")                  // Predicate
                        .filters(f -> f.stripPrefix(1)          // Filter
                                .addRequestHeader("X-Request", "Hello"))
                        .uri("lb://user-service"))              // Backend service
                .build();
    }

//    @Bean
//    public RouterFunction<ServerResponse> userRoutes() {
//        return GatewayRouterFunctions.route("user-service-8081")
//                .GET("/users/**", HandlerFunctions.http())
//                .POST("/users/**", HandlerFunctions.http())
//                .PUT("/users/**", HandlerFunctions.http())
//                .before(BeforeFilterFunctions.uri("http://localhost:8081"))
//                .filter(FilterFunctions.prefixPath("/api/v1"))
//                .build()
//
//                .and(GatewayRouterFunctions.route("user-service-8082")
//                        .GET("/users/**", HandlerFunctions.http())
//                        .POST("/users/**", HandlerFunctions.http())
//                        .PUT("/users/**", HandlerFunctions.http())
//                        .before(BeforeFilterFunctions.uri("http://localhost:8082"))
//                        .filter(FilterFunctions.prefixPath("/api/v1"))
//                        .build()
//                );
//    }
}