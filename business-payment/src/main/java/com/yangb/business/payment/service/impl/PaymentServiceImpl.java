package com.yangb.business.payment.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yangb.api.common.entities.business.payment.Payment;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.payment.dao.PaymentMapper;
import com.yangb.business.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Service
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public Payment findById(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }

    @Override
/*    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})*/
    @HystrixCommand
    public ResultVo errrMethod(Integer id){
        int x = 1/0;
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultVo.makeSuccess("服务降级演示!");
    }

    public ResultVo timeoutHandler(Integer id){
        return ResultVo.makeFailed("服务降级" + id);
    }

    public ResultVo globalFallbackMethod(){
        return ResultVo.makeFailed("调用异常,这是降级方案!");
    }
}
