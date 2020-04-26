package com.yangb.business.order.service;

import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.ResultVo;

/**
 * Created by yangb on 2020/4/26
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
public interface OrderService {

    ResultVo getOrderDetails(AppUser user);
}
