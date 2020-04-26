package com.yangb.business.payment.service;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.yangb.api.common.entities.Payment;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
public interface PaymentService {

    Payment findById(Long id);

    /**
     * 异常的方法, 用于演示微服务调用时产生熔断,降级
     * @return
     */
    String errrMethod(Integer id);
}
