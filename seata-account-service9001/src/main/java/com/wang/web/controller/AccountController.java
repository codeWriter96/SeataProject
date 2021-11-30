package com.wang.web.controller;

import com.wang.constant.LogConstant;
import com.wang.constant.MessageConstant;
import com.wang.entity.Account;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.service.AccountService;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author username
 * @date 2021/11/28 11:17
 * @description:TODO
 * @since 8
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/select/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Long id){
        if (null==id){
            CommonResult<Object> result = new CommonResult<>(444, MessageConstant.M1, null);
            log.error(LogConstant.L2, DateUtil.getDate(),id,result.toString());
            return result;
        }
        Account account = accountService.selectByPrimaryKey(id);
        if (null != account){
            log.info(LogConstant.L2, DateUtil.getDate(),id,account.toString());
            return new CommonResult<Account>(200,MessageConstant.M2,account);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),id,null);
        return new CommonResult<Account>(444,MessageConstant.M3,null);
    }

    @PostMapping("/update")
    public Object updateMoney(@RequestBody Order order){
        boolean res = accountService.updateMoney(order);
        if (res){
            log.info(LogConstant.L2,DateUtil.getDate(),order.toString(),true);
            return new CommonResult<>(200,MessageConstant.M2,null);
        }
        log.error(LogConstant.L2,DateUtil.getDate(),order.toString(),false);
        return new CommonResult<>(444,MessageConstant.M5,null);
    }

}
