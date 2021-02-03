package com.atguigu.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.concurrent.TimeUnit;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/27 10:58
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "testA.......";
    }

    @GetMapping("testB")
    public String testB(){
        log.info("testD 测试异常比例");
        int num = 10/0;
        return "testB.......";
    }

    @GetMapping("/testC")
    public String testC(){
        log.info("testD 测试异常数");
        int num = 10/0;
        return "testC........";
    }

    @GetMapping("/testD")
    @SentinelResource(value = "testHotKey",blockHandler = "myblockhandler")         //value值可以用来设置热点限流的资源名,blockhandler的值用来指向限流后执行的方法，
    public String testD(@RequestParam(value = "p1",required = false)String p1,      //不设置blockHandler则执行sentinel默认的方法。注意该指向的方法必须传入BlockException
                        @RequestParam(value = "p2",required = false)String p2){     //required=false 指并不是一定要传的参数
        log.info("testD 测试热点限流");
        return "testD........";
    }

    public String myblockhandler(String p1, String p2, BlockException exception){
        return "自定义的热点降级";
    }

}
