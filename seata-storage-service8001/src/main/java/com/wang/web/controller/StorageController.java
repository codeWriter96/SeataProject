package com.wang.web.controller;

import com.wang.constant.LogConstant;
import com.wang.constant.MessageConstant;
import com.wang.entity.CommonResult;
import com.wang.entity.Order;
import com.wang.entity.Storage;
import com.wang.service.StorageService;
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
@RequestMapping("/storage")
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/select/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Long id){
        if (null==id){
            CommonResult<Object> result = new CommonResult<>(444, MessageConstant.M1, null);
            log.error(LogConstant.L2, DateUtil.getDate(),id,result.toString());
            return result;
        }
        Storage storage = storageService.selectByPrimaryKey(id);
        if (null != storage){
            log.info(LogConstant.L2, DateUtil.getDate(),id,storage.toString());
            return new CommonResult<>(200,MessageConstant.M2,storage);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),id,null);
        return new CommonResult<Storage>(444,MessageConstant.M3,null);
    }
    @PostMapping("/update")
    public Object updateStorage(@RequestBody Order order){


        long count = order.getCount();
        if (0==count || null == order.getProductId()){
            log.error(LogConstant.L2, DateUtil.getDate(),count,null);
            return new CommonResult<>(444,MessageConstant.M4,null);
        }
        boolean res = storageService.updateByPrimaryKeySelective(order);
        if (!res){
            log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),null);
            return new CommonResult<>(444,MessageConstant.M4,null);
        }
        log.info(LogConstant.L2, DateUtil.getDate(),count,null);
        return new CommonResult<>(200,MessageConstant.M2,null);
    }


}
