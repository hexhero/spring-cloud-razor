package com.yangb.business.payment.config;

import com.alibaba.fastjson.JSON;
import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.CurrentUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * com.yangb.business.order.config
 * Create by YangBin on 2020/4/25
 * Copyright © 2020 YangBin. All rights reserved.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserResolver());
    }

    public class CurrentUserResolver implements HandlerMethodArgumentResolver {
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.hasParameterAnnotation(CurrentUser.class);
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser appUser = JSON.parseObject(principal.toString(), AppUser.class);
            return appUser;
        }
    }

}
