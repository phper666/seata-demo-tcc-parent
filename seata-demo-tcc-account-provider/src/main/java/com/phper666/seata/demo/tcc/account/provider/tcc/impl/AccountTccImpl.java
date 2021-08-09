package com.phper666.seata.demo.tcc.account.provider.tcc.impl;

import cn.hutool.json.JSONUtil;
import com.phper666.seata.demo.tcc.account.provider.core.AccountCore;
import com.phper666.seata.demo.tcc.account.provider.entity.AccountDO;
import com.phper666.seata.demo.tcc.account.provider.tcc.AccountTcc;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:21:47
 * @software IntelliJ IDEA
 */
@Service
public class AccountTccImpl implements AccountTcc {
    @Autowired
    private AccountCore accountCore;

    @Override
    public boolean tccDecreaseAccount(BusinessActionContext businessActionContext, AccountDO accountDO) {
        // TODO 检查单前是否是否已经回滚过，解决防悬挂问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则不再执行，解决幂等性问题

        // 冻结金额
        return accountCore.freezeAmount(accountDO.getUserId(), accountDO.getAmount());
    }

    @Override
    public TwoPhaseResult tccDecreaseAccountCommit(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        // 真正减少金额
        AccountDO accountDO = JSONUtil.toBean(businessActionContext.getActionContext("accountDO").toString(), AccountDO.class);
        boolean isSuccess = accountCore.freezeAccountCommit(accountDO.getUserId(), accountDO.getAmount());
        String msg = isSuccess ? "success" : "faild";
        return new TwoPhaseResult(isSuccess, msg);
    }

    @Override
    public TwoPhaseResult tccDecreaseAccountRollback(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题

        // 回滚冻结的金额
        AccountDO accountDO = JSONUtil.toBean(businessActionContext.getActionContext("accountDO").toString(), AccountDO.class);
        boolean fl = accountCore.freezeAccountRollback(accountDO.getUserId(), accountDO.getAmount());
        String msg = fl ? "success" : "faild";
        return new TwoPhaseResult(fl, msg);
    }
}
