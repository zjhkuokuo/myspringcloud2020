package com.atguigu.alibabaSeata.service;

import com.atguigu.alibabaSeata.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/3 22:31
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    //使用feign调用远程的storage服务提供者
    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count);
}
