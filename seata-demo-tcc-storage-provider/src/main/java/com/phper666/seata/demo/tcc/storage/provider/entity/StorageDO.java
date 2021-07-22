package com.phper666.seata.demo.tcc.storage.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("storage")
public class StorageDO {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String commodityCode;
    private String name;
    private Integer freezeCount;
    private Integer count;
}
