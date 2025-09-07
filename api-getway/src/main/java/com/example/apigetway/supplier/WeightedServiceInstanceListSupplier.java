package com.example.apigetway.supplier;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WeightedServiceInstanceListSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    public WeightedServiceInstanceListSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        List<ServiceInstance> instances = new ArrayList<>();

        // Example: user-service with weighted distribution
        instances.add(new WeightedServiceInstance("user-service", "localhost", 8081, false, 3)); // weight 3
        instances.add(new WeightedServiceInstance("user-service", "localhost", 8082, false, 1)); // weight 1

        // Expand list based on weights
        List<ServiceInstance> expanded = new ArrayList<>();
        for (ServiceInstance instance : instances) {
            int weight = ((WeightedServiceInstance) instance).getWeight();
            for (int i = 0; i < weight; i++) {
                expanded.add(instance);
            }
        }

        return Flux.just(expanded);
    }

    // Custom instance with weight
    static class WeightedServiceInstance implements ServiceInstance {
        private final String serviceId;
        private final String host;
        private final int port;
        private final boolean secure;
        private final int weight;

        public WeightedServiceInstance(String serviceId, String host, int port, boolean secure, int weight) {
            this.serviceId = serviceId;
            this.host = host;
            this.port = port;
            this.secure = secure;
            this.weight = weight;
        }

        @Override
        public String getServiceId() { return serviceId; }
        @Override
        public String getHost() { return host; }
        @Override
        public int getPort() { return port; }
        @Override
        public boolean isSecure() { return secure; }
        @Override
        public URI getUri() { return URI.create("http" + (secure ? "s" : "") + "://" + host + ":" + port); }
        @Override
        public java.util.Map<String, String> getMetadata() { return java.util.Collections.emptyMap(); }

        public int getWeight() { return weight; }
    }
}

