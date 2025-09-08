package com.example.apigetway.supplier;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

//@Component
public class StaticUserServiceSupplier implements ServiceInstanceListSupplier {
    @Override
    public String getServiceId() {
        return "user-service";
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance("user1", "user-service", "localhost", 8081, false),
                new DefaultServiceInstance("user2", "user-service", "localhost", 8082, false)
        ));
    }

//    @Override
//    public Flux<List<ServiceInstance>> get() {
//        return Flux.defer(() -> {
//            List<ServiceInstance> instances = Arrays.asList(
//                    new DefaultServiceInstance("user1", "user-service", "localhost", 8081, false),
//                    new DefaultServiceInstance("user2", "user-service", "localhost", 8082, false)
//            );
//            return Flux.just(instances);
//        });
//    }

//    @Override
//    public Flux<List<ServiceInstance>> get() {
//        return Flux.interval(Duration.ofSeconds(10)) // refresh every 10s
//                .map(tick -> Arrays.asList(
//                        new DefaultServiceInstance("user1", "user-service", "localhost", 8081, false),
//                        new DefaultServiceInstance("user2", "user-service", "localhost", 8082, false)
//                ));
//    }
}

