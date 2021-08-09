package com.phper666.seata.demo.tcc.account.provider.rpc;

import com.phper666.seata.demo.tcc.account.provider.dto.StorageDTO;

/**
 * The interface Storage service.
 */
public interface StorageDubboService {

    /**
     * 扣减库存
     */
    boolean tccDecreaseStorage(StorageDTO storageDTO);
}
