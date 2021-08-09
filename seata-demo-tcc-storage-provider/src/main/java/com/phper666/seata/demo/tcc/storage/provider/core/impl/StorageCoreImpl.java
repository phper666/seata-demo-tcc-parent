package com.phper666.seata.demo.tcc.storage.provider.core.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phper666.seata.demo.tcc.storage.provider.core.StorageCore;
import com.phper666.seata.demo.tcc.storage.provider.entity.StorageDO;
import com.phper666.seata.demo.tcc.storage.provider.mapper.StorageMapper;
import org.springframework.stereotype.Service;

/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
@Service
public class StorageCoreImpl extends ServiceImpl<StorageMapper, StorageDO> implements StorageCore {
    @Override
    public boolean freezeStorage(String commodityCode, Integer count) {
        String sql = "freeze_count = freeze_count + " + count;
        String sql1 = "count = count - " + count;
        UpdateWrapper<StorageDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .setSql(sql1)
                .eq(StorageDO::getCommodityCode, commodityCode);
        return update(updateWrapper);
    }

    @Override
    public boolean freezeStorageCommit(String commodityCode, Integer count) {
        String sql = "freeze_count = freeze_count - " + count;
        UpdateWrapper<StorageDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .eq(StorageDO::getCommodityCode, commodityCode);
        return update(updateWrapper);
    }

    @Override
    public boolean freezeStorageRollback(String commodityCode, Integer count) {
        String sql = "count = count + " + count;
        String sql1 = "freeze_count = freeze_count - " + count;
        UpdateWrapper<StorageDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .setSql(sql)
                .setSql(sql1)
                .eq(StorageDO::getCommodityCode, commodityCode);
        return update(updateWrapper);
    }
}
