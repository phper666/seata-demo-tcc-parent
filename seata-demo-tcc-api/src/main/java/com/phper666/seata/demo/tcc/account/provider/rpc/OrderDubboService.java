package com.phper666.seata.demo.tcc.account.provider.rpc;


import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;

public interface OrderDubboService {

    /**
     * 创建订单
     */
    boolean tccCreateOrder(OrderDTO orderDTO);

}
