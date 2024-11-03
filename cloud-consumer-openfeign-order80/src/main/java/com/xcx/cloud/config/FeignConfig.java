package com.xcx.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        // return Retryer.NEVER_RETRY; // 默认不重试
        return new Retryer.Default(100, 1, 3);
        // period毫秒后开始重试，最大重试间隔maxAttempts秒，最大重试次数maxAttempts
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // 全部日志
    }
}
