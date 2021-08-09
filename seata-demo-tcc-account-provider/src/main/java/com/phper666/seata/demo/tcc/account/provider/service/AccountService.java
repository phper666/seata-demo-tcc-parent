package com.phper666.seata.demo.tcc.account.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;

public interface AccountService {
    /**
     * 冻结用户钱
     */
    boolean tccDecreaseAccount(AccountDTO accountDto);

    boolean addAmount(AccountDTO accountDTO);

    boolean decreaseAmount(AccountDTO accountDTO);
}
