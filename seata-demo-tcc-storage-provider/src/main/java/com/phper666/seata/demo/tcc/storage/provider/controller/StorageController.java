package com.phper666.seata.demo.tcc.storage.provider.controller;

import com.phper666.seata.demo.tcc.account.provider.dto.CommodityDTO;
import com.phper666.seata.demo.tcc.storage.provider.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @PostMapping("dec_storage")
    public boolean decreaseStorage(@RequestBody CommodityDTO commodityDTO) {
        log.info("请求库存微服务：{}", commodityDTO.toString());
        return storageService.tccDecreaseStorage(new BusinessActionContext(), commodityDTO);
    }
}

