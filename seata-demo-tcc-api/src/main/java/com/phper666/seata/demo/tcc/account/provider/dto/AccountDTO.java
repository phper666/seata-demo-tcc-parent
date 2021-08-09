package com.phper666.seata.demo.tcc.account.provider.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AccountDTO implements Serializable {
    private Integer id;

    private String userId;

    private BigDecimal amount;
}
