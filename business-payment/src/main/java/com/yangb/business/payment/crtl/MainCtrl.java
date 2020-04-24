package com.yangb.business.payment.crtl;

import com.yangb.api.common.entities.Payment;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌. All rights reserved.
 */
@RestController
public class MainCtrl {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping({"/","/index"})
    public ResultVo index(){
        return ResultVo.makeSuccess("Everything is ok!");
    }

    @GetMapping("/payment/{id}")
    public ResultVo<Payment> getPayment(@PathVariable Long id){
        Payment payment = paymentService.findById(id);
        return ResultVo.makeSuccess(payment);
    }

    @GetMapping("/payment/my")
    @Secured("ROLE_ADMIN")
    public ResultVo<String> myPayment(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultVo.makeSuccess("成功",principal + " 的支付信息");
    }
}
