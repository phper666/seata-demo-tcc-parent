package com.phper666.seata.demo.tcc.account.provider.dubbo;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.rpc.AccountDubboService;
import com.phper666.seata.demo.tcc.account.provider.service.AccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
@Slf4j
public class AccountDubboServiceImpl implements AccountDubboService {
    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional
    public boolean tccDecreaseAccount(AccountDTO accountDTO) {
        log.info("全局事务id ：" + RootContext.getXID());
        return accountService.tccDecreaseAccount(accountDTO);
    }
}
