package com.yangb.business.order.service.impl;

import com.yangb.api.common.entities.Payment;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.order.service.PaymentFeignService;
import org.springframework.stereotype.Service;

/**
 * Created by yangb on 2020/4/26
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Service
public class PaymentFeignServiceFallbackImpl implements PaymentFeignService {
    @Override
    public ResultVo<Payment> getPayment(Long id) {
        return ResultVo.makeFailed("服务响应失败");
    }

    @Override
    public ResultVo<String> myPayment() {
        return ResultVo.makeFailed("服务响应失败");
    }

    @Override
    public ResultVo error() {
        return ResultVo.makeFailed("服务响应失败");
    }
}
