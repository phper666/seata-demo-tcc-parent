package com.phper666.seata.demo.tcc.account.provider.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StorageDTO implements Serializable {
    private String commodityCode;

    private String name;

    private Integer count;
}
