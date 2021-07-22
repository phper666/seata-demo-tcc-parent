package com.phper666.seata.demo.tcc.account.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("account")
public class AccountDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String userId;
    private Double amount;
}
