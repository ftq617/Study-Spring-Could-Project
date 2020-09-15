package com.luke.study.gateway.config;

import org.springframework.cloud.gateway.filter.factory.AddRequestParameterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @ClassName: GatewayConfig
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/13 13:58
 **/
@Configuration
public class GatewayConfig {

    //@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route",
                        r -> r.path("/get")
                                .filters(f -> f.addResponseHeader("X-Response-Default-Foo", "Default-Bar")
                                        .addRequestParameter("id","123"))
                            .uri("https://localhost:8301/get")
                            //.filter()  增加自定义过滤器
                )
                .build();
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
