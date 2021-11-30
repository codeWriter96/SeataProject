package com.wang.exceptionHandler;

import com.wang.constant.LogConstant;
import com.wang.entity.CommonResult;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author username
 * @date 2021/11/30 9:56
 * @description:TODO
 * @since 8
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    public static final String M1 = "页面出错了";

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception e){
        log.error(LogConstant.L2, DateUtil.getDate(),e.getMessage(),null);
        return CommonResult.errorResult(M1);
    }
}
