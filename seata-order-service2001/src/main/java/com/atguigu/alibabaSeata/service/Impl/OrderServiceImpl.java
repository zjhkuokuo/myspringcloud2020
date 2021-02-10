package com.atguigu.alibabaSeata.service.Impl;

import com.atguigu.alibabaSeata.dao.OrderDao;
import com.atguigu.alibabaSeata.domain.Order;
import com.atguigu.alibabaSeata.service.AccountService;
import com.atguigu.alibabaSeata.service.OrderService;
import com.atguigu.alibabaSeata.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/3 22:24
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

     /**
      *   创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
      */
    @Override
    @GlobalTransactional        //seata事务注解
    public void create(Order order) {
        log.info("----->新建订单开始");
        orderDao.create(order);      //新建订单

        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //修改订单状态，从零到1代表已经完成
        orderDao.update(order.getUserId(),0);
        log.info("----->新建订单结束");
    }
}
