package com.powerwolf.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powerwolf.springcloud.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMapper extends BaseMapper<Payment> {
}
