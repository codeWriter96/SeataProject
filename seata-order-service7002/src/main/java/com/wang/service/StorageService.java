package com.wang.service;

import com.wang.entity.Order;
import com.wang.service.impl.StorageServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author username
 * @date 2021/11/28 22:02
 * @description:TODO
 * @since 8
 */
@FeignClient(value = "SEATA-STORAGE-SERVICE",fallback = StorageServiceImpl.class)
public interface StorageService {

    @GetMapping("/storage/select/{id}")
    Object selectByPrimaryKey(@PathVariable("id") Long id);

    @PostMapping("/storage/update")
    Object updateStorage(@RequestBody Order order);
}
