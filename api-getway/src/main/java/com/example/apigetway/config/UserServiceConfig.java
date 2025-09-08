package com.example.apigetway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

//@Configuration
//@ConfigurationProperties(prefix = "custom.user-service")
public class UserServiceConfig {
    private List<Instance> instances;

    @Data
    public static class Instance {
        private String host;
        private int port;
        // getters and setters
    }

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {
            @Override
            public String getServiceId() {
                return "user-service";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.defer(() -> {
                    List<ServiceInstance> list = instances.stream()
                            .map(i -> new DefaultServiceInstance(
                                    i.getHost() + ":" + i.getPort(),
                                    "user-service",
                                    i.getHost(),
                                    i.getPort(),
                                    false))
                            .collect(Collectors.toList());
                    return Flux.just(list);
                });
            }
        };
    }
}
