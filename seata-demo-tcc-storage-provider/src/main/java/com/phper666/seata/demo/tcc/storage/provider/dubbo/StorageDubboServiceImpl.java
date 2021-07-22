package com.phper666.seata.demo.tcc.storage.provider.dubbo;


import com.phper666.seata.demo.tcc.account.provider.dto.CommodityDTO;
import com.phper666.seata.demo.tcc.account.provider.rpc.StorageDubboService;
import com.phper666.seata.demo.tcc.storage.provider.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
@Slf4j
public class StorageDubboServiceImpl implements StorageDubboService {

    @Autowired
    private StorageService storageService;

    @Override
    @GlobalTransactional
    public boolean tccDecreaseStorage(CommodityDTO commodityDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return storageService.tccDecreaseStorage(new BusinessActionContext(), commodityDTO);
    }
}
