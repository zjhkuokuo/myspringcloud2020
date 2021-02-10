package com.atguigu.alibabaSeata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;
    private Long productId;     //产品id
    private Integer total;      //库存总数
    private Integer used;       //已用库存
    private Integer residue;    //剩余库存
}
