package com.yangb.serve.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by yangb on 2020/4/24
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServer {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class, args);
    }
}
