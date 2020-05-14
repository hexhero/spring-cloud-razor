package com.yangb.api.common.utils;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * Created by YangBin on 2020/5/12
 * Copyright (c) 2020 杨斌 All rights reserved.
 */
@RegisterMapper
public interface SaveMapper<T> {

    /**
     * 保存一个实体，如果实体的主键不为null则更新, 没有主键或主键为null, 则插入.
     *
     * @param record
     * @return
     */
    @InsertProvider(type = SaveProvider.class, method = "dynamicSQL")
    int save(T record);
}
