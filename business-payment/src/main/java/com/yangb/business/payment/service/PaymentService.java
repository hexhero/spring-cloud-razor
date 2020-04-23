package com.yangb.business.payment.service;

import com.yangb.api.common.entities.Payment;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
public interface PaymentService {

    Payment findById(Long id);
}
