package com.phper666.seata.demo.tcc.storage.provider.tcc.impl;

import cn.hutool.json.JSONUtil;
import com.phper666.seata.demo.tcc.storage.provider.core.StorageCore;
import com.phper666.seata.demo.tcc.storage.provider.entity.StorageDO;
import com.phper666.seata.demo.tcc.storage.provider.tcc.StorageTcc;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:35:54
 * @software IntelliJ IDEA
 */
@Service
public class StorageTccImpl implements StorageTcc {
    @Autowired
    private StorageCore storageCore;

    @Override
    public boolean tccDecreaseStorage(BusinessActionContext businessActionContext, StorageDO storageDO) {
        // TODO 检查单前是否是否已经回滚过，解决防悬挂问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则不再执行，解决幂等性问题

        return storageCore.freezeStorage(storageDO.getCommodityCode(), storageDO.getCount());
    }

    @Override
    public TwoPhaseResult tccDecreaseStorageCommit(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        StorageDO storageDO = JSONUtil.toBean(businessActionContext.getActionContext("storageDO").toString(), StorageDO.class);
        boolean isSuccess = storageCore.freezeStorageCommit(storageDO.getCommodityCode(), storageDO.getCount());
        String msg = isSuccess ? "success" : "faild";
        return new TwoPhaseResult(isSuccess, msg);
    }

    @Override
    public boolean tccDecreaseStorageRollback(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题

        StorageDO storageDO = JSONUtil.toBean(businessActionContext.getActionContext("storageDO").toString(), StorageDO.class);
        return storageCore.freezeStorageRollback(storageDO.getCommodityCode(), storageDO.getCount());
    }
}
