package com.example.apigetway.filters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomHeaderFilter extends AbstractGatewayFilterFactory<CustomHeaderFilter.Config> {

    public CustomHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getRequest().mutate()
                    .header("X-Custom-Request", config.requestValue)
                    .build();

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                exchange.getResponse().getHeaders()
                        .add("X-Custom-Response", config.responseValue);
            }));
        };
    }

    @Data
    public static class Config {
        private String requestValue;
        private String responseValue;

        // getters & setters
//        public String getRequestValue() { return requestValue; }
//        public void setRequestValue(String requestValue) { this.requestValue = requestValue; }
//        public String getResponseValue() { return responseValue; }
//        public void setResponseValue(String responseValue) { this.responseValue = responseValue; }
    }
}

