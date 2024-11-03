package com.xcx.cloud.myGateWay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISITED_TIME = "begin_visited_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求时间
        exchange.getAttributes().put(BEGIN_VISITED_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitedTime = exchange.getAttribute(BEGIN_VISITED_TIME);
            if (beginVisitedTime != null) {
                log.info("访问接口主机: " + exchange.getRequest().getURI().getHost());
                log.info("访问接口端口: " + exchange.getRequest().getURI().getPort());
                log.info("访问接口URL: " + exchange.getRequest().getURI().getPath());
                log.info("访问接口URL参数: " + exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长: " + (System.currentTimeMillis() - beginVisitedTime) + "ms");
                log.info("###################################################");
                System.out.println();
            }
        }));
    }

    /**
     * 优先级，值越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
