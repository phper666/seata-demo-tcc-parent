package com.phper666.seata.demo.tcc.order.provider.core.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phper666.seata.demo.tcc.order.provider.core.OrderCore;
import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;
import com.phper666.seata.demo.tcc.order.provider.mapper.OrderMapper;
import org.springframework.stereotype.Service;


/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
@Service
public class OrderCoreImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderCore {
    @Override
    public boolean createOrder(OrderDO orderDO) {
        // insert into t_order values(null,#{order.orderNo},#{order.userId},#{order.commodityCode},${order.count},${order.amount})
        return save(orderDO);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return removeById(id);
    }
}
