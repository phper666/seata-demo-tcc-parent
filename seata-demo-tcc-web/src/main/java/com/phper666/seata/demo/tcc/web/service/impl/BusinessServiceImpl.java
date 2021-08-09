package com.phper666.seata.demo.tcc.web.service.impl;

import com.phper666.seata.demo.tcc.account.provider.dto.BusinessDTO;
import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import com.phper666.seata.demo.tcc.account.provider.dto.StorageDTO;
import com.phper666.seata.demo.tcc.account.provider.enums.RspStatusEnum;
import com.phper666.seata.demo.tcc.account.provider.exception.DefaultException;
import com.phper666.seata.demo.tcc.account.provider.response.ObjectResponse;
import com.phper666.seata.demo.tcc.account.provider.rpc.OrderDubboService;
import com.phper666.seata.demo.tcc.account.provider.rpc.StorageDubboService;
import com.phper666.seata.demo.tcc.web.service.BusinessService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @DubboReference(check = false)
    private StorageDubboService storageDubboService;

    @DubboReference(check = false)
    private OrderDubboService orderDubboService;

    /**
     * 处理业务逻辑 正常的业务逻辑
     *
     * @Param:
     * @Return:
     */
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata")
    @Override
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        //1、扣减库存
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setCommodityCode(businessDTO.getCommodityCode());
        storageDTO.setCount(businessDTO.getCount());
        boolean isSuccess = storageDubboService.tccDecreaseStorage(storageDTO);
        if (!isSuccess) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }
        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        isSuccess = orderDubboService.tccCreateOrder(orderDTO);

        if (!isSuccess) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return objectResponse;
    }

    /**
     * 出处理业务服务，出现异常回顾
     *
     * @param businessDTO
     * @return
     */
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    @Override
    public ObjectResponse handleBusiness2(BusinessDTO businessDTO, boolean flag) {
        log.info("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        //1、扣减库存
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setCommodityCode(businessDTO.getCommodityCode());
        storageDTO.setCount(businessDTO.getCount());
        boolean isSuccess = storageDubboService.tccDecreaseStorage(storageDTO);
        if (!isSuccess) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        isSuccess = orderDubboService.tccCreateOrder(orderDTO);

        if (!flag) {
            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
        }

        if (!isSuccess) {
            throw new DefaultException(RspStatusEnum.FAIL);
        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return objectResponse;
    }
}
