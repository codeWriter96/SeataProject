package com.wang.service.impl;

import com.wang.constant.LogConstant;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.service.StorageService;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author username
 * @date 2021/11/28 22:03
 * @description:TODO
 * @since 8
 */
@Slf4j
@Component
public class StorageServiceImpl implements StorageService {

    public static final String M1 = "服务被降级处理";
    @Override
    public Object selectByPrimaryKey(Long id) {
        log.error(LogConstant.L2, DateUtil.getDate(),id,M1);
        return CommonResult.errorResult(M1);
    }

    @Override
    public Object updateStorage(Order order) {
        log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),M1);
        return CommonResult.errorResult(M1);
    }
}
