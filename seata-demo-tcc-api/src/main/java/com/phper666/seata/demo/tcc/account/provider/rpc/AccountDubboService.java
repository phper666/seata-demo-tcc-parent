package com.phper666.seata.demo.tcc.account.provider.rpc;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;

/**
 * The interface Account service.
 */
public interface AccountDubboService {

    /**
     * 余额扣款
     *
     * @return
     */
    boolean tccDecreaseAccount(AccountDTO accountDTO);
}
