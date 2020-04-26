package com.yangb.business.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by YangBin on 2020/4/22
 * Copyright (c) 2020 杨斌. All rights reserved.
 */
@SpringBootApplication
@MapperScan("com.yangb.business.payment.dao")
@EnableCircuitBreaker
@EnableDiscoveryClient
public class PaymentServer {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServer.class, args);
    }
}
