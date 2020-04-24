package com.yangb.serve.oauth2.dao;

import com.yangb.api.common.entities.serve.oauth2.AppUser;
import com.yangb.api.common.utils.Dao;

public interface AppUserMapper extends Dao<AppUser> {

    AppUser queryByUsername(String username);
}