package com.phper666.seata.demo.tcc.account.provider.core;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phper666.seata.demo.tcc.account.provider.entity.AccountDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author yuzhao.li
 * @email yuzhao.li@salesforce-china.com
 * @date 5/13/2021
 **/
public interface AccountCore extends IService<AccountDO> {
    boolean freezeAccountCommit(@Param("userId") String userId, @Param("amount") Double amount);

    boolean freezeAccountRollback(@Param("userId") String userId, @Param("amount") Double amount);

    boolean freezeAmount(@Param("userId") String userId, @Param("freezeAmount") Double freezeAmount);

    boolean addAmount(@Param("userId") String userId, @Param("amount") Double amount);

    boolean decreaseAmount(@Param("userId") String userId, @Param("amount") Double amount);
}
