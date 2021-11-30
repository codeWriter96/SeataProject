package com.wang.service;

import com.wang.entity.Order;

import java.util.Map;

/**
 * @author username
 * @date 2021/11/28 11:13
 * @description:TODO
 * @since 8
 */
public interface OrderService {
    Order selectByPrimaryKey(Long id);

    Boolean insert(Order record);

    String selectByPrimaryKey1(Long id);

    boolean buyGood(Order order);
}
