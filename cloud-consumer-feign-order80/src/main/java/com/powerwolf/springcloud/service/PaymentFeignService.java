package com.powerwolf.springcloud.service;

import com.powerwolf.springcloud.entity.CommonResult;
import com.powerwolf.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getById(@PathVariable("id") String id);

    @GetMapping("/payment/timeout")
    String timeout();
}
