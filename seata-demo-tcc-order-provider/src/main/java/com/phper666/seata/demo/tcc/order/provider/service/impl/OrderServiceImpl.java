package com.phper666.seata.demo.tcc.order.provider.service.impl;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import com.phper666.seata.demo.tcc.account.provider.rpc.AccountDubboService;
import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;
import com.phper666.seata.demo.tcc.order.provider.service.OrderService;
import com.phper666.seata.demo.tcc.order.provider.tcc.OrderTcc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    @DubboReference(check = false)
    private AccountDubboService accountDubboService;

    @Autowired
    private OrderTcc orderTcc;

    /**
     * 创建订单
     *
     * @Param: OrderDTO  订单对象
     * @Return: OrderDTO  订单对象
     */
    @Override
    public boolean tccCreateOrder(OrderDTO orderDTO) {
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
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderDTO, orderDO);
        orderDO.setCount(orderDTO.getOrderCount());
        orderDO.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            orderTcc.tccCreateOrder(null, orderDO);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
