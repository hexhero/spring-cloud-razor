package com.yangb.business.order.service;

import com.yangb.api.common.entities.business.payment.Payment;
import com.yangb.api.common.utils.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by yangb on 2020/4/23
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@FeignClient(value = "business-payment")//,fallback = PaymentFeignServiceFallbackImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/{id}")
    ResultVo<Payment> getPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/my")
    ResultVo<String> myPayment();

    @GetMapping("/payment/timeout")
    ResultVo error();
}
