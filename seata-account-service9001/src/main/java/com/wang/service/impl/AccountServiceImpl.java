package com.wang.service.impl;

import com.wang.constant.LogConstant;
import com.wang.dao.AccountDao;
import com.wang.entity.Account;
import com.wang.entity.Order;
import com.wang.service.AccountService;
import com.wang.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author username
 * @date 2021/11/28 11:14
 * @description:TODO
 * @since 8
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account selectByPrimaryKey(Long id) {
        return accountDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean updateMoney(Order order) {
        BigDecimal orderMoney = order.getMoney();
        Long userId = order.getUserId();
        Account account = accountDao.selectByUserId(userId);
        if (null==account){
            log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),false);
            return false;
        }
        BigDecimal residue = account.getResidue();
        BigDecimal residueNew = residue.subtract(orderMoney);
        int i = residueNew.compareTo(new BigDecimal("0"));
        if (i>=0){
            Account accountNew = new Account();
            accountNew.setResidue(residueNew);
            accountNew.setId(account.getId());
            accountDao.updateByPrimaryKeySelective(accountNew);
            log.error(LogConstant.L2, DateUtil.getDate(),order.toString(),true);
            return true;
        }
        return false;
    }
}
