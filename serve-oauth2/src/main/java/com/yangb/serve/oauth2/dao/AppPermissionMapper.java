package com.yangb.serve.oauth2.dao;

import com.yangb.api.common.entities.serve.oauth2.AppPermission;
import com.yangb.api.common.utils.Dao;

import java.util.List;

public interface AppPermissionMapper extends Dao<AppPermission> {

    List<AppPermission> queryByUserid(int userid);
}