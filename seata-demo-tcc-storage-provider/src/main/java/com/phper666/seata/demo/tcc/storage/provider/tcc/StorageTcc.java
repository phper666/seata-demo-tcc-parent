package com.phper666.seata.demo.tcc.storage.provider.tcc;

import com.phper666.seata.demo.tcc.storage.provider.entity.StorageDO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:34:45
 * @software IntelliJ IDEA
 */
@LocalTCC
public interface StorageTcc {
    @TwoPhaseBusinessAction(name = "tccDecreaseStorage", commitMethod = "tccDecreaseStorageCommit", rollbackMethod = "tccDecreaseStorageRollback")
    boolean tccDecreaseStorage(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "storageDO") StorageDO storageDO);

    TwoPhaseResult tccDecreaseStorageCommit(BusinessActionContext businessActionContext);

    boolean tccDecreaseStorageRollback(BusinessActionContext businessActionContext);
}
