package com.yangb.serve.discover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoverServer {
    public static void main(String[] args) {
        SpringApplication.run(DiscoverServer.class, args);
    }
}
