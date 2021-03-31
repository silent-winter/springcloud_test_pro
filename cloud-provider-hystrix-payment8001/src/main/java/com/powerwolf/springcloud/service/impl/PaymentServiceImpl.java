package com.powerwolf.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.powerwolf.springcloud.entity.Payment;
import com.powerwolf.springcloud.mapper.PaymentMapper;
import com.powerwolf.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        System.out.println("线程池: " + Thread.currentThread().getName() + "    paymentInfo_OK,id:" + id);
        return "线程池: " + Thread.currentThread().getName() + "    paymentInfo_OK,id:" + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int time = 3;
        //int age = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程池: " + Thread.currentThread().getName() + "    paymentInfo_Timeout,id:" + id + "耗时:" + time);
        return "线程池: " + Thread.currentThread().getName() + "    paymentInfo_Timeout,id:" + id + "耗时:" + time;
    }

    //兜底方法，服务降级
    @Override
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "出错啦";
    }



    //=========服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("id不能为负数");
        }
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号: " + IdUtil.simpleUUID();
    }

    @Override
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数,fallback";
    }
}
