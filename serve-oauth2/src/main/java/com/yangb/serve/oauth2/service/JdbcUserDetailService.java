package com.yangb.serve.oauth2.service;

import com.alibaba.fastjson.JSON;
import com.yangb.api.common.entities.serve.oauth2.AppPermission;
import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.serve.oauth2.dao.AppPermissionMapper;
import com.yangb.serve.oauth2.dao.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangb on 2020/4/24
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@Service
public class JdbcUserDetailService implements UserDetailsService {

    @Resource
    private AppUserMapper appUserMapper;
    @Resource
    private AppPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserMapper.queryByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<AppPermission> permissions = permissionMapper.queryByUserid(user.getId());
        String password = user.getPassword();
        user.setPassword(null);
        UserDetails userDetails = User.withUsername(JSON.toJSONString(user)).password(password)
                .authorities(permissions.stream().map(item -> item.getCode()).toArray(String[]::new)).build();
        return userDetails;
    }
}
