package com.wang.service;

import com.wang.entity.Account;
import com.wang.entity.Order;

/**
 * @author username
 * @date 2021/11/28 11:13
 * @description:TODO
 * @since 8
 */
public interface AccountService {
    Account selectByPrimaryKey(Long id);

    boolean updateMoney(Order order);
}
