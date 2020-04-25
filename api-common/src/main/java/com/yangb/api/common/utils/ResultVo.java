package com.yangb.api.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result View Object 结果展示对象
 *
 * Created by yangb on 2020/4/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T extends Object> {

    public static final int SUCCESS_CODE = 200;
    public static final int FAILED_CODE = 500;

    private int status;
    private boolean success;
    private String message;
    private T data;

    public static ResultVo makeSuccess(String message) {
        return makeResult(SUCCESS_CODE, true, message, null);
    }

    public static <T> ResultVo makeSuccess(T data) {
        return makeResult(SUCCESS_CODE, true, "成功", data);
    }

    public static ResultVo makeFailed(String message) {
        return makeResult(FAILED_CODE, false, message, null);
    }

    public static ResultVo makeFailed(int status, String message){
        return makeResult(status, false, message, null);
    }

    private static <T> ResultVo makeResult(int status, boolean success, String message, T data) {
        return new ResultVo<T>(status, success, message, data);
    }
}
