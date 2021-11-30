package com.wang.web.controller;

import com.wang.constant.LogConstant;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.service.OrderService;
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
@RequestMapping("/order")
@Slf4j
public class OrderController {
    public static final String M1 = "success";
    public static final String M2 = "查询id有误";
    public static final String M3 = "订单内容有误";

    @Autowired
    private OrderService orderService;

    @GetMapping("/select/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Long id){
        if (null==id){
            log.error(LogConstant.L2, DateUtil.getDate(),id,null);
            return CommonResult.errorResult(M2);
        }
        Order order = orderService.selectByPrimaryKey(id);
        if (null != order){
            log.info(LogConstant.L2, DateUtil.getDate(),id,order.toString());
            return CommonResult.successResult(M1,order);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),id,null);
        return CommonResult.errorResult(M2);
    }

    /**
     *
     * @param id 订单的id
     * @return 处理结果
     */
    @GetMapping("/selectAll/{id}")
    public Object selectByPrimaryKey1(@PathVariable("id") Long id){
        if (null==id){
            log.error(LogConstant.L2, DateUtil.getDate(),id,null);
            return CommonResult.errorResult(M2);
        }
        String res = orderService.selectByPrimaryKey1(id);
        if (null == res){
            log.info(LogConstant.L2, DateUtil.getDate(),id,null);
            return CommonResult.errorResult(M2);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),id,res);
        return CommonResult.successResult(M1,res);
    }

    /**
     *
     * @param order 订单对象
     * @return 处理结果
     */
    @PostMapping("/buy")
    public Object buyGood(@RequestBody Order order){
        if (null == order.getProductId() || null==order.getUserId() || null == order.getCount() || null ==order.getMoney()){
            log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),"fail");
            return CommonResult.errorResult(M3);
        }
        boolean res = orderService.buyGood(order);
        if (!res){
            log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),null);
            return CommonResult.errorResult(M3);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),order.toString(),"success");
        return CommonResult.successResult(M1);
    }
}
