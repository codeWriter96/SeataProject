package com.wang.service.impl;


import com.wang.constant.LogConstant;
import com.wang.dao.StorageDao;
import com.wang.entity.Order;
import com.wang.entity.Storage;
import com.wang.service.StorageService;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author username
 * @date 2021/11/28 11:14
 * @description:TODO
 * @since 8
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;

    @Override
    public Storage selectByPrimaryKey(Long id) {
        return storageDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
    public boolean updateByPrimaryKeySelective(Order order) {

        long productId = order.getProductId();
        Storage storage = storageDao.selectByProductId(productId);
        if (null == storage){
            return false;
        }
        Integer residue = storage.getResidue();

        if (residue >= order.getCount()){
            Storage storageNew = new Storage();
            Integer count = order.getCount();
            storageNew.setResidue(residue-count);
            storageNew.setId(storage.getId());
            int i = storageDao.updateByPrimaryKeySelective(storageNew);

            //模拟库存在更改后出现延迟！
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return i >= 0;
        }
        return false;
    }
}
