package com.phper666.seata.demo.tcc.account.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.phper666.seata.demo.tcc.account.provider.core.AccountCore;
import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.entity.AccountDO;
import com.phper666.seata.demo.tcc.account.provider.service.AccountService;
import com.phper666.seata.demo.tcc.account.provider.tcc.AccountTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountTcc accountTcc;

    @Autowired
    private AccountCore accountCore;

    @Override
    public boolean tccDecreaseAccount(AccountDTO accountDTO) {
        // 冻结金额
        AccountDO accountDO = BeanUtil.copyProperties(accountDTO, AccountDO.class);
        return accountTcc.tccDecreaseAccount(null, accountDO);
    }

    @Override
    public boolean addAmount(AccountDTO accountDTO) {
        return accountCore.addAmount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
    }

    @Override
    public boolean decreaseAmount(AccountDTO accountDTO) {
        return accountCore.decreaseAmount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
    }
}
