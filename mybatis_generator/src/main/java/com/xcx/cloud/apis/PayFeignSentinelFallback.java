package com.xcx.cloud.apis;

import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelFallback implements AlibabaFeignApi{
    @Override
    public String getPayInfo(Integer id) {
        return "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o";
    }
}
