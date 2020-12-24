package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import com.atguigu.springcloud.Dao.PaymentDao;
import com.atguigu.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/9 16:49
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //@Autowired 与 @Resource 作用一样
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
