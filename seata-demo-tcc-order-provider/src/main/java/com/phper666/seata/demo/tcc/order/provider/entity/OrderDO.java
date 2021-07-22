package com.phper666.seata.demo.tcc.order.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("`order`")
public class OrderDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String orderNo;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Double amount;
}
