package com.powerwolf.springcloud.controller;

import com.powerwolf.springcloud.entity.CommonResult;
import com.powerwolf.springcloud.entity.Payment;
import com.powerwolf.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") String id){
        return paymentFeignService.getById(id);
    }

    @GetMapping("/payment/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }
}
