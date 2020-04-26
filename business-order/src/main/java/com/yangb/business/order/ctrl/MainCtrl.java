package com.yangb.business.order.ctrl;

import com.yangb.api.common.entities.Payment;
import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.CurrentUser;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.order.service.PaymentFeignService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@RestController
public class MainCtrl {

    @Resource
    private PaymentFeignService paymentFeignService;

    @RequestMapping
    public ResultVo index() {
        return ResultVo.makeSuccess("Good job!");
    }

    @RequestMapping("/order/pay/{id}")
    public ResultVo getOrderPayment(@PathVariable Long id){
        ResultVo<Payment> payment = paymentFeignService.getPayment(id);
        return payment;
    }

    /**
     * 演示 获取当前登陆用户，此接口只有 ROLE_ADMIN 权限才能访问。
     * @param user
     * @return
     */
    @RequestMapping("/order/admin")
    @Secured("ROLE_ADMIN")
    public ResultVo admin(@CurrentUser AppUser user){
        return ResultVo.makeSuccess(user);
    }

    /**
     * 演示 令牌中继 授权传递
     * @return
     */
    @RequestMapping("/order/pay/my")
    public ResultVo myOrderPay(@CurrentUser AppUser user){
        ResultVo<Payment> resultVo = paymentFeignService.getPayment(user.getId().longValue());
        Payment payment = resultVo.getData();
        return ResultVo.makeSuccess(user.getFullname() + "订单：xxx" + "支付流水" + payment.getSerial());
    }

    /**
     * 服务熔断演示
     */
    @RequestMapping("/order/error")
    public ResultVo error(){
        return paymentFeignService.error();
    }

}
