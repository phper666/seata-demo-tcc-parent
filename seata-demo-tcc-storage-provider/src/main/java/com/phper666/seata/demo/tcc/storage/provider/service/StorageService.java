package com.phper666.seata.demo.tcc.storage.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.StorageDTO;

public interface StorageService {
    boolean tccDecreaseStorage(StorageDTO storageDTO);
}
