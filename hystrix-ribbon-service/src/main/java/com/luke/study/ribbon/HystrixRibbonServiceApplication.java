package com.luke.study.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker//开启Hystrix的断路器功能
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixRibbonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixRibbonServiceApplication.class, args);
    }

}
