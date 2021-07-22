package com.phper666.seata.demo.tcc.order.provider.service;

import com.phper666.seata.demo.tcc.account.provider.dto.OrderDTO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderService {
    /**
     * 创建订单
     */
    @TwoPhaseBusinessAction(name = "tccCreateOrder", commitMethod = "tccCreateOrderCommit", rollbackMethod = "tccCreateOrderRollback")
    boolean tccCreateOrder(BusinessActionContext actionContext, OrderDTO orderDTO);

    TwoPhaseResult tccCreateOrderCommit(BusinessActionContext actionContext);

    boolean tccCreateOrderRollback(BusinessActionContext actionContext);
}
