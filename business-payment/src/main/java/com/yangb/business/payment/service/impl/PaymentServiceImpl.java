package com.yangb.business.payment.service.impl;

import com.yangb.api.common.entities.Payment;
import com.yangb.business.payment.dao.PaymentMapper;
import com.yangb.business.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Payment findById(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }
}
