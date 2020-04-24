package com.yangb.api.common.utils;

import java.lang.annotation.*;

/**
 * Create by YangBin on 2020/4/25
 * Copyright © 2020 YangBin. All rights reserved.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
