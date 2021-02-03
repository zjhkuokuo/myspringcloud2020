package com.atguigu.cloudalibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/29 17:18
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException e){
        return new CommonResult(444,"自定义降级，全局的降级方法----1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"自定义降级，全局的降级方法----2");
    }
}
