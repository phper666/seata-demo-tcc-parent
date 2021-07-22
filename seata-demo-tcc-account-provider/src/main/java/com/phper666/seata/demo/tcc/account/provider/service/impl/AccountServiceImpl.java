package com.phper666.seata.demo.tcc.account.provider.service.impl;

import com.phper666.seata.demo.tcc.account.provider.core.AccountCore;
import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.service.AccountService;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountCore accountCore;

    @Override
    public boolean tccDecreaseAccount(AccountDTO accountDto) {
        // TODO 检查单前是否是否已经回滚过，解决防悬挂问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则不再执行，解决幂等性问题

        // 冻结金额
        return accountCore.freezeAmount(accountDto.getUserId(), accountDto.getAmount().doubleValue());
    }

    @Override
    public TwoPhaseResult tccDecreaseAccountCommit(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        // 真正减少金额
        AccountDTO accountDto = (AccountDTO) businessActionContext.getActionContext("accountDto");
        boolean isSuccess = accountCore.freezeAccountCommit(accountDto.getUserId(), accountDto.getAmount().doubleValue());
        String msg = isSuccess ? "success" : "faild";
        return new TwoPhaseResult(isSuccess, msg);
    }

    @Override
    public TwoPhaseResult tccDecreaseAccountRollback(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题

        // 回滚冻结的金额
        AccountDTO accountDto = (AccountDTO) businessActionContext.getActionContext("accountDto");
        boolean fl = accountCore.freezeAccountRollback(accountDto.getUserId(), accountDto.getAmount().doubleValue());
        String msg = fl ? "success" : "faild";
        return new TwoPhaseResult(fl, msg);
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
