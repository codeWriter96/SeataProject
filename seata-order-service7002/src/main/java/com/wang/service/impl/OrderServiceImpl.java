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
        //id的返回值在order实体对象内！
        int res = orderDao.insertGetId(order);
        if (0 == res) {
            throw new RuntimeException();
        }

        //降级则抛出异常并回滚
        Object object = storageService.updateStorage(order);
        if (object instanceof CommonResult) {
            throw new RuntimeException();
        }

        LinkedHashMap updateStorage = (LinkedHashMap)object;

        if (updateStorage.isEmpty()){
            throw new RuntimeException();
        }

        if (200 != (Integer) updateStorage.get("code")){
            throw new RuntimeException("库存不足");
        }


        Object object1 = accountService.updateMoney(order);
        if (object1 instanceof CommonResult) {
            throw new RuntimeException();
        }
        //降级则抛出异常并回滚
        LinkedHashMap updateMoney = (LinkedHashMap) object1;
        //非空
        if (null == updateMoney){
            throw new RuntimeException();
        }
        if (200 != (Integer) updateMoney.get("code")){
            throw new RuntimeException("余额不足");
        }

        order.setStatus(1);
        int i = orderDao.updateByPrimaryKeySelective(order);
        return i > 0 ;

    }

    @Override
    public boolean updateByPrimaryKeySelective(Long id, Long userId) {
        Order order = new Order();
        order.setId(id);
        order.setUserId(userId);
        int i = orderDao.updateByPrimaryKeySelective(order);
        return i>0;
    }
}
