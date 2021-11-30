package com.wang.service.impl;


import com.wang.dao.OrderDao;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.service.AccountService;
import com.wang.service.OrderService;
import com.wang.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author username
 * @date 2021/11/28 11:14
 * @description:TODO
 * @since 8
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;


    @Override
    public Order selectByPrimaryKey(Long id) {
        return orderDao.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(Order order) {
        order.setStatus(0);
        int i = orderDao.insert(order);
        return i != 0;
    }

    @Override
    public String selectByPrimaryKey1(Long id) {
        Order order = orderDao.selectByPrimaryKey(id);
        if (null == order){
            return null;
        }
        Object o = accountService.selectByPrimaryKey((long) 1);
        System.out.println(o);
        Object o1 = storageService.selectByPrimaryKey((long) 1);
        System.out.println(o1);
        return "success";
    }


    @GlobalTransactional(name = "buyGood",rollbackFor = Exception.class)
    @Override
    public boolean buyGood(Order order) {
        order.setStatus(0);
        int i = orderDao.insert(order);
        if (i < 0){
            throw new RuntimeException("数据库插入错误");
        }

        //降级则抛出异常并回滚
        Object object = storageService.updateStorage(order);
        if (object instanceof CommonResult){
            throw new RuntimeException("服务被降级");
        }else {
            LinkedHashMap updateStorage = (LinkedHashMap)object;
            assert updateStorage.isEmpty();
            if (200 != (Integer) updateStorage.get("code")){
                throw new RuntimeException("库存不足");
            }
        }

        Object object1 = accountService.updateMoney(order);
        //降级则抛出异常并回滚
        if (object1 instanceof CommonResult){
            throw new RuntimeException("服务被降级");
        }else {

            LinkedHashMap updateMoney = (LinkedHashMap) object1;
            //非空断言
            assert updateMoney.isEmpty();
            if (200 != (Integer) updateMoney.get("code")){
                throw new RuntimeException("余额不足");
            }
        }
        return true;
    }
}
