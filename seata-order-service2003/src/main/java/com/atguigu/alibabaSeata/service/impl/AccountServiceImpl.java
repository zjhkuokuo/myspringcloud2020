package com.atguigu.alibabaSeata.service.impl;

import com.atguigu.alibabaSeata.dao.AccountDao;
import com.atguigu.alibabaSeata.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 21:04
 */
@Service
@Slf4j
public class AccountServiceImpl  implements AccountService {

    @Resource
    private AccountDao accountDao;

    /**
     *  扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {

        log.info("------->account-service中扣减账户余额开始");
        //模拟异常，测试seata分布式事务注解@GlobalTransactional
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountDao.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
