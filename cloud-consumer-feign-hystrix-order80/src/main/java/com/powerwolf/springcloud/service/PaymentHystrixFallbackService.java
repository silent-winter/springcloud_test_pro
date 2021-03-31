package com.powerwolf.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务异常:fall back";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "服务异常:timeout  fall back";
    }
}
