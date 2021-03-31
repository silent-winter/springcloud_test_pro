package com.powerwolf.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.powerwolf.springcloud.entity.Payment;

public interface PaymentService extends IService<Payment> {
    String paymentInfo_OK(Integer id);

    String paymentInfo_Timeout(Integer id);

    String paymentInfo_TimeoutHandler(Integer id);

    String paymentCircuitBreaker(Integer id);

    String paymentCircuitBreaker_fallback(Integer id);
}
