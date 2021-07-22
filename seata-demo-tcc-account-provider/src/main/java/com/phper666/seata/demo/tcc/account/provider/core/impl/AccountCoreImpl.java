package com.phper666.seata.demo.tcc.account.provider.core.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phper666.seata.demo.tcc.account.provider.core.AccountCore;
import com.phper666.seata.demo.tcc.account.provider.entity.AccountDO;
import com.phper666.seata.demo.tcc.account.provider.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
@Service
public class AccountCoreImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountCore {
    @Override
    public boolean freezeAccountCommit(String userId, Double freezeAmount) {
        String sql = "freeze_amount = freeze_amount - " + freezeAmount;
        UpdateWrapper<AccountDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .eq(AccountDO::getUserId, userId);
        return update(updateWrapper);
    }

    @Override
    public boolean freezeAccountRollback(String userId, Double freezeAmount) {
        String sql = "freeze_amount = freeze_amount - " + freezeAmount;
        String sql1 = "amount = amount + " + freezeAmount;
        UpdateWrapper<AccountDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .setSql(sql1)
                .eq(AccountDO::getUserId, userId);
        return update(updateWrapper);
    }

    @Override
    public boolean freezeAmount(String userId, Double freezeAmount) {
        String sql = "amount = amount - " + freezeAmount;
        String sql1 = "freeze_amount = freeze_amount + " + freezeAmount;
        UpdateWrapper<AccountDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .setSql(sql1)
                .eq(AccountDO::getUserId, userId);
        return update(updateWrapper);
    }

    @Override
    public boolean addAmount(String userId, Double amount) {
        String sql = "amount = amount + " + amount;
        UpdateWrapper<AccountDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .eq(AccountDO::getUserId, userId);
        return update(updateWrapper);
    }

    @Override
    public boolean decreaseAmount(String userId, Double amount) {
        String sql = "amount = amount - " + amount;
        UpdateWrapper<AccountDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .eq(AccountDO::getUserId, userId);
        return update(updateWrapper);
    }
}
