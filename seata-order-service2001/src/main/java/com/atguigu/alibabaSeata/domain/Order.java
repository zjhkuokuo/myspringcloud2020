package com.atguigu.alibabaSeata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/3 22:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;     //订单状态：0：创建中，1：已创建
}
