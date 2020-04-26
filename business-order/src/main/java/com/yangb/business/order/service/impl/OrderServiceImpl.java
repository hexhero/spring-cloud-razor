package com.yangb.business.order.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yangb.api.common.entities.business.payment.Payment;
import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.order.service.OrderService;
import com.yangb.business.order.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yangb on 2020/4/26
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @HystrixCommand(defaultFallback = "commonFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name="execution.isolation.strategy",value = "SEMAPHORE")
    })
    @Override
    public ResultVo getOrderDetails(AppUser user) {
        ResultVo<Payment> payment = paymentFeignService.getPayment(1L);
        return ResultVo.makeSuccess(user.getFullname() + " 您的订单信息:xxx " + "支付流水: " + payment.getData().getSerial());
    }

    public ResultVo commonFallback(){
        return ResultVo.makeFailed("服务暂不可用!");
    }
}
