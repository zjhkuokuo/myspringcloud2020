package com.atguigu.alibabaSeata.conctroller;

import com.atguigu.alibabaSeata.domain.CommonResult;
import com.atguigu.alibabaSeata.domain.Order;
import com.atguigu.alibabaSeata.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/3 22:49
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建完成！");
    }
}
