package com.phper666.seata.demo.tcc.storage.provider.service.impl;


import com.phper666.seata.demo.tcc.account.provider.dto.CommodityDTO;
import com.phper666.seata.demo.tcc.storage.provider.core.StorageCore;
import com.phper666.seata.demo.tcc.storage.provider.service.StorageService;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageCore storageCore;

    @Override
    public boolean tccDecreaseStorage(BusinessActionContext businessActionContext, CommodityDTO commodityDTO) {
        // TODO 检查单前是否是否已经回滚过，解决防悬挂问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则不再执行，解决幂等性问题

        businessActionContext.getActionContext().put("commodityCode", commodityDTO.getCommodityCode());
        businessActionContext.getActionContext().put("count", commodityDTO.getCount());
        return storageCore.freezeStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
    }

    @Override
    public TwoPhaseResult tccDecreaseStorageCommit(BusinessActionContext actionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer count = (Integer) actionContext.getActionContext("count");
        boolean isSuccess = storageCore.freezeStorageCommit(commodityCode, count);
        String msg = isSuccess ? "success" : "faild";
        return new TwoPhaseResult(isSuccess, msg);
    }

    @Override
    public boolean tccDecreaseStorageRollback(BusinessActionContext actionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题

        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer count = (Integer) actionContext.getActionContext("count");
        return storageCore.freezeStorageRollback(commodityCode, count);
    }
}
