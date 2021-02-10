package com.atguigu.alibabaSeata.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 22:47
 */
public interface StorageService {

    void decrease(Long productId,Integer count);
}
