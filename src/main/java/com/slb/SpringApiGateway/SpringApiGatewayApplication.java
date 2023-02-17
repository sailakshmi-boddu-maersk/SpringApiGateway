package com.slb.SpringApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringApiGatewayApplication {

	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(
                        r -> r.path("/catalog/**")
                                .uri("lb://movie-catalog-service")
                )
                .route(
                        r -> r.path("/movies/**")
                                .uri("lb://movie-info-service")
                )
                .route(
                        r -> r.path("/ratings/**")
                                .uri("lb://rating-data-service")
                )
                .build();
    }

	
	public static void main(String[] args) {
		SpringApplication.run(SpringApiGatewayApplication.class, args);
	}

}
