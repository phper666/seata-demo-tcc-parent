package com.phper666.seata.demo.tcc.storage.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.CommodityDTO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface StorageService {

    @TwoPhaseBusinessAction(name = "tccDecreaseStorage", commitMethod = "tccDecreaseStorageCommit", rollbackMethod = "tccDecreaseStorageRollback")
    boolean tccDecreaseStorage(BusinessActionContext actionContext, CommodityDTO commodityDTO);

    TwoPhaseResult tccDecreaseStorageCommit(BusinessActionContext actionContext);

    boolean tccDecreaseStorageRollback(BusinessActionContext actionContext);
}
