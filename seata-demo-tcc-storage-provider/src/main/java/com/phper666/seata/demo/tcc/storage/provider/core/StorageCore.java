package com.phper666.seata.demo.tcc.storage.provider.core;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phper666.seata.demo.tcc.storage.provider.entity.StorageDO;

/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
public interface StorageCore extends IService<StorageDO> {
    /**
     * 冻结商品库存
     *
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    boolean freezeStorage(String commodityCode, Integer count);

    boolean freezeStorageCommit(String commodityCode, Integer count);

    boolean freezeStorageRollback(String commodityCode, Integer count);
}
