package com.wang.service;

import com.wang.entity.Order;
import com.wang.service.impl.AccountServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author username
 * @date 2021/11/28 21:41
 * @description:TODO
 * @since 8
 */
@FeignClient(value = "SEATA-ACCOUNT-SERVICE",fallback = AccountServiceImpl.class)
public interface AccountService {

    @GetMapping("/account/select/{id}")
    Object selectByPrimaryKey(@PathVariable("id") Long id);

    @PostMapping("/account/update")
    Object updateMoney(@RequestBody Order order);

}
