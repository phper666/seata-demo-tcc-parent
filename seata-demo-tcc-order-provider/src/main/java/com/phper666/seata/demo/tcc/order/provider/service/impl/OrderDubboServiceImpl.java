package com.phper666.seata.demo.tcc.order.provider.service.impl;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import com.phper666.seata.demo.tcc.account.provider.rpc.AccountDubboService;
import com.phper666.seata.demo.tcc.order.provider.core.OrderCore;
import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;
import com.phper666.seata.demo.tcc.order.provider.service.OrderService;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderDubboServiceImpl implements OrderService {

    @DubboReference(check = false)
    private AccountDubboService accountDubboService;

    @Autowired
    private OrderCore orderCore;

    /**
     * 创建订单
     *
     * @Param: OrderDTO  订单对象
     * @Return: OrderDTO  订单对象
     */
    @Override
    public boolean tccCreateOrder(BusinessActionContext businessActionContext, OrderDTO orderDTO) {
        //扣减用户账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        boolean isSuccess = accountDubboService.tccDecreaseAccount(accountDTO);
        if (!isSuccess) {
            throw new RuntimeException("error");
        }

        //生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        //生成订单
        OrderDO order = new OrderDO();
        BeanUtils.copyProperties(orderDTO, order);
        order.setCount(orderDTO.getOrderCount());
        order.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            orderCore.createOrder(order);
            businessActionContext.getActionContext().put("orderId", order.getId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public TwoPhaseResult tccCreateOrderCommit(BusinessActionContext actionContext) {
        // TODO 检查当前事务是否已经执行过Commit接口，如果已经执行过，则不再执行，解决幂等性问题

        // 因为在Try接口就生成订单，所以直接返回true即可
        return new TwoPhaseResult(true, "success");
    }

    @Override
    public boolean tccCreateOrderRollback(BusinessActionContext actionContext) {
        // TODO 检查当前事务是否已经执行过Rollback接口，如果已经执行过，则不再执行，解决幂等性问题

        // TODO 检查当前事务是否已经执行过Try接口，如果已经执行过，则执行后面逻辑，如果没有执行过则直接返回true，解决空回滚问题
        Long id = (Long) actionContext.getActionContext("orderId");
        return orderCore.deleteOrder(id);
    }
}
