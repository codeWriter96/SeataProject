package com.wang.service;

import com.wang.entity.Order;
import com.wang.entity.Storage;

/**
 * @author username
 * @date 2021/11/28 11:13
 * @description:TODO
 * @since 8
 */
public interface StorageService {
    Storage selectByPrimaryKey(Long id);

    boolean updateByPrimaryKeySelective(Order order);
}
