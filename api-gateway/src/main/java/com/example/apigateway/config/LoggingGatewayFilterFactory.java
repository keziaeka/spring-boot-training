package com.example.apigateway.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingGatewayFilterFactory extends
        AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    final Logger logger =
            LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("[Pre] request id = {}", request.getId());

            // Pre-processing
            if (config.isPreLogger()) {
                logger.info("Pre GatewayFilter logging: "
                        + config.getBaseMessage());
            }
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Post-processing
                        ServerHttpResponse response = exchange.getResponse();
                        log.info("[Post] response status = {}", response.getStatusCode());

                        if (config.isPostLogger()) {
                            logger.info("Post GatewayFilter logging: "
                                    + config.getBaseMessage());
                        }
                    }));
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
