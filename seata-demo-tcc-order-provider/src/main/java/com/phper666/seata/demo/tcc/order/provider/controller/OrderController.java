package com.phper666.seata.demo.tcc.order.provider.controller;

import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import com.phper666.seata.demo.tcc.order.provider.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create_order")
    public boolean tccCreateOrder(@RequestBody OrderDTO orderDTO) {
        log.info("请求订单微服务：{}", orderDTO.toString());
        return orderService.tccCreateOrder(orderDTO);
    }
}

