package com.phper666.seata.demo.tcc.web.controller;

import com.phper666.seata.demo.tcc.account.provider.dto.BusinessDTO;
import com.phper666.seata.demo.tcc.account.provider.response.ObjectResponse;
import com.phper666.seata.demo.tcc.web.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web")
@Slf4j
public class BusinessController {


    @Autowired
    private BusinessService businessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy2/{flag}")
    ObjectResponse handleBusiness2(@PathVariable boolean flag, @RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return businessService.handleBusiness2(businessDTO, flag);
    }
}
