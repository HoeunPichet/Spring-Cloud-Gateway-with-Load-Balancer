package com.example.apigetway.config;

import com.example.apigetway.supplier.WeightedServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class LoadBalancerConfig {

    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new WeightedServiceInstanceListSupplier("user-service");
    }
}