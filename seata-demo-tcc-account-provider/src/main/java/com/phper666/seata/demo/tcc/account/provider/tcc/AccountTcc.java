package com.phper666.seata.demo.tcc.account.provider.tcc;

import com.phper666.seata.demo.tcc.account.provider.entity.AccountDO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:21:32
 * @software IntelliJ IDEA
 */
@LocalTCC
public interface AccountTcc {
    /**
     * 冻结用户钱
     */
    @TwoPhaseBusinessAction(name = "tccDecreaseAccount", commitMethod = "tccDecreaseAccountCommit", rollbackMethod = "tccDecreaseAccountRollback")
    boolean tccDecreaseAccount(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "accountDO") AccountDO accountDO);

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
}
