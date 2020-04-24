package com.yangb.serve.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by yangb on 2020/4/22
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@SpringBootApplication
@MapperScan("com.yangb.serve.oauth2.dao")
public class OAuth2Server {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Server.class, args);
    }
}
