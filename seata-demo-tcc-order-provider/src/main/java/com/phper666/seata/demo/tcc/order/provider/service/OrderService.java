package com.phper666.seata.demo.tcc.order.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     */
    boolean tccCreateOrder(OrderDTO orderDTO);
}
