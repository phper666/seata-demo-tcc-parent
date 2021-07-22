package com.phper666.seata.demo.tcc.account.provider.controller;

import com.phper666.seata.demo.tcc.account.provider.dto.AccountDTO;
import com.phper666.seata.demo.tcc.account.provider.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add_account")
    boolean addAccount(@RequestBody AccountDTO accountDto) {
        log.info("请求账户微服务：{}", accountDto.toString());
        return accountService.addAmount(accountDto);
    }

    @PostMapping("/decrease_account")
    boolean decreaseAccount(@RequestBody AccountDTO accountDto) {
        log.info("请求账户微服务：{}", accountDto.toString());
        return accountService.decreaseAmount(accountDto);
    }
}

