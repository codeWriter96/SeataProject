package com.wang.service.impl;

import com.wang.constant.LogConstant;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.service.AccountService;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author username
 * @date 2021/11/28 21:45
 * @description:TODO
 * @since 8
 */
@Component
@Slf4j
public class AccountServiceImpl implements AccountService {
    public static final String M1 = "服务被降级处理";

    @Override
    public Object selectByPrimaryKey(Long id) {
        log.error(LogConstant.L2, DateUtil.getDate(),id,M1);
        return new CommonResult<>(444,M1,null);
    }

    @Override
    public Object updateMoney(Order order) {
        log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),M1);
        return new CommonResult<>(444,M1,null);
    }
}
