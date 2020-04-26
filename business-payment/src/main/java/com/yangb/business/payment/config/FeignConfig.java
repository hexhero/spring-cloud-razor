package com.yangb.business.payment.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Created by yangb on 2020/4/23
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Configuration
public class FeignConfig {

    /**
     * JWT 令牌中继
     */
    @Configuration
    public class TokenRequestInterceptor implements RequestInterceptor{

        @Override
        public void apply(RequestTemplate requestTemplate) {
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            if(authentication == null) return;
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header("Authorization","Bearer " + details.getTokenValue());
        }
    }
}
