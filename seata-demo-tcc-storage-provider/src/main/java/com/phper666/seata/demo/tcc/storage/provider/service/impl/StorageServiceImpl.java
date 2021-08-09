package com.phper666.seata.demo.tcc.storage.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.phper666.seata.demo.tcc.account.provider.dto.StorageDTO;
import com.phper666.seata.demo.tcc.storage.provider.entity.StorageDO;
import com.phper666.seata.demo.tcc.storage.provider.service.StorageService;
import com.phper666.seata.demo.tcc.storage.provider.tcc.StorageTcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageTcc storageTcc;

    @Override
    public boolean tccDecreaseStorage(StorageDTO storageDTO) {
        StorageDO storageDO = BeanUtil.copyProperties(storageDTO, StorageDO.class);
        return storageTcc.tccDecreaseStorage(null, storageDO);
    }
}
