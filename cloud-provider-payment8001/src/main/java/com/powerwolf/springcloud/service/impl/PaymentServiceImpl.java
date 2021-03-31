package com.powerwolf.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powerwolf.springcloud.entity.Payment;
import com.powerwolf.springcloud.mapper.PaymentMapper;
import com.powerwolf.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
