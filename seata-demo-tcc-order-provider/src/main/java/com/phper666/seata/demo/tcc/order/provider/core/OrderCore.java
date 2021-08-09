package com.phper666.seata.demo.tcc.order.provider.core;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;


/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
public interface OrderCore extends IService<OrderDO> {
    boolean createOrder(OrderDO orderDO);

    boolean deleteOrder(Long id);

    boolean deleteOrder(String orderNo);
}
