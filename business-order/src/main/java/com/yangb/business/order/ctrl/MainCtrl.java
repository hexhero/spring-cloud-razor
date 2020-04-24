package com.yangb.business.order.ctrl;

import com.yangb.api.common.entities.Payment;
import com.yangb.api.common.utils.ResultVo;
import com.yangb.business.order.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@RestController
public class MainCtrl {

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @RequestMapping("/order/admin")
    @Secured("ROLE_ADMIN")
    public ResultVo admin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultVo.makeSuccess(principal);
    }

    @GetMapping("/clients")
    public ResultVo clients() {
        String clients = discoveryClient.getServices().stream()
                .map(item -> discoveryClient.getInstances(item).stream()
                        .map(instance ->
                                instance.getHost() +  instance.getPort() + instance.getUri() + instance.getScheme()
                            ).collect(Collectors.joining("|")))
                .collect(Collectors.joining("/"));
        return ResultVo.makeSuccess("微服务信息", clients);
    }
}
