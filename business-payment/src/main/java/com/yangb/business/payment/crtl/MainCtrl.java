package com.yangb.business.payment.crtl;

import com.yangb.api.common.entities.business.payment.Payment;
import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.CurrentUser;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.payment.service.PaymentService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌. All rights reserved.
 */
@RestController
public class MainCtrl {

    @Resource
    private PaymentService paymentService;

    @RequestMapping({"/","/index"})
    public ResultVo index(){
        return ResultVo.makeSuccess("Everything is ok!");
    }

    @GetMapping("/payment/{id}")
    @Secured("ROLE_ADMIN")
    public ResultVo<Payment> getPayment(@PathVariable Long id){
        Payment payment = paymentService.findById(id);
        return ResultVo.makeSuccess(payment);
    }

    @GetMapping("/payment/my")
    @Secured("ROLE_ADMIN")
    public ResultVo<String> myPayment(@CurrentUser AppUser user){
        return ResultVo.makeSuccess(user.getFullname() + "支付信息：xxx");
    }

    @GetMapping("/payment/timeout")
    public ResultVo error(){
        ResultVo rs = paymentService.errrMethod(10);
        return rs;
    }
}
