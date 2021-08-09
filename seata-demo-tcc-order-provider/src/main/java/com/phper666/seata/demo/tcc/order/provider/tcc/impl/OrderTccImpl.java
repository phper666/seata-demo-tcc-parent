package com.phper666.seata.demo.tcc.order.provider.tcc.impl;

import cn.hutool.json.JSONUtil;
import com.phper666.seata.demo.tcc.order.provider.core.OrderCore;
import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;
import com.phper666.seata.demo.tcc.order.provider.tcc.OrderTcc;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:03:17
 * @software IntelliJ IDEA
 */
@Service
public class OrderTccImpl implements OrderTcc {
    @Autowired
    private OrderCore orderCore;

    /**
     * 创建订单
     *
     * @Param: OrderDO  订单对象
     * @Return: OrderDO  订单对象
     */
    @Override
    public boolean tccCreateOrder(BusinessActionContext businessActionContext, OrderDO orderDO) {
        try {
            orderCore.createOrder(orderDO);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public TwoPhaseResult tccCreateOrderCommit(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        // 因为在Try接口就生成订单，所以直接返回true即可
        return new TwoPhaseResult(true, "success");
    }

    @Override
    public boolean tccCreateOrderRollback(BusinessActionContext businessActionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题

        OrderDO orderDO = JSONUtil.toBean(businessActionContext.getActionContext("orderDO").toString(), OrderDO.class);
        String orderNo = orderDO.getOrderNo();
        return orderCore.deleteOrder(orderNo);
    }
}
