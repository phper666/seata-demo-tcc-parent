package com.phper666.seata.demo.tcc.account.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface AccountService {
    /**
     * 冻结用户钱
     */
    @TwoPhaseBusinessAction(name = "tccDecreaseAccount", commitMethod = "tccDecreaseAccountCommit", rollbackMethod = "tccDecreaseAccountRollback")
    boolean tccDecreaseAccount(@BusinessActionContextParameter(paramName = "accountDto") AccountDTO accountDto);

    /**
     * 真正减掉用户钱
     *
     * @param businessActionContext
     * @return
     */
    TwoPhaseResult tccDecreaseAccountCommit(BusinessActionContext businessActionContext);

    /**
     * 回滚冻结的钱
     *
     * @param businessActionContext
     * @return
     */
    TwoPhaseResult tccDecreaseAccountRollback(BusinessActionContext businessActionContext);

    boolean addAmount(AccountDTO accountDTO);

    boolean decreaseAmount(AccountDTO accountDTO);
}
