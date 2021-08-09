package com.phper666.seata.demo.tcc.order.provider.dubbo;

import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import com.phper666.seata.demo.tcc.account.provider.rpc.OrderDubboService;
import com.phper666.seata.demo.tcc.order.provider.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
@Slf4j
public class OrderDubboDubboServiceImpl implements OrderDubboService {
    @Autowired
    private OrderService orderService;

    @Override
    @GlobalTransactional
    public boolean tccCreateOrder(OrderDTO orderDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return orderService.tccCreateOrder(orderDTO);
    }
}
