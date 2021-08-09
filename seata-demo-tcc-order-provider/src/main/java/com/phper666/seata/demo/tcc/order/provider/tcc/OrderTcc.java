package com.phper666.seata.demo.tcc.order.provider.tcc;

import com.phper666.seata.demo.tcc.order.provider.entity.OrderDO;
import io.seata.rm.tcc.TwoPhaseResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author yuzhao.li
 * @email 562405704@qq.com
 * @date 2021-08-09 22:02:41
 * @software IntelliJ IDEA
 */
@LocalTCC
public interface OrderTcc {
    /**
     * 创建订单
     */
    @TwoPhaseBusinessAction(name = "tccCreateOrder", commitMethod = "tccCreateOrderCommit", rollbackMethod = "tccCreateOrderRollback")
    boolean tccCreateOrder(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "orderDO") OrderDO orderDO);

    TwoPhaseResult tccCreateOrderCommit(BusinessActionContext businessActionContext);

    boolean tccCreateOrderRollback(BusinessActionContext businessActionContext);
}
