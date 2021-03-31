package com.powerwolf.springcloud.controller;

import com.powerwolf.springcloud.entity.CommonResult;
import com.powerwolf.springcloud.entity.Payment;
import com.powerwolf.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment){
        boolean res = paymentService.save(payment);
        log.info("插入结果:" + res);
        return res ? new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, res)
                : new CommonResult(444, "插入数据库失败,serverPort:" + serverPort, null);
    }

    @GetMapping("get/{id}")
    public CommonResult getPayment(@PathVariable("id") String id){
        Payment payment = paymentService.getById(id);
        return payment != null ? new CommonResult(200, "查询成功,serverPort:" + serverPort, payment)
                : new CommonResult(444, "查询失败,serverPort:" + serverPort, null);
    }

    @GetMapping("lb")
    public String getLB(){
        return serverPort;
    }
}
