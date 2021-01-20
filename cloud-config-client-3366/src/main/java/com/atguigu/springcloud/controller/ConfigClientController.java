package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/19 21:58
 */
@RestController
@RefreshScope       //刷新配置注解
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    //config.info能获取到时因为bootstrap.yml优先级最高，它已经先去github上获取了数据。
    @Value("${config.info}")    //对应github上的git下springcloud-config2里的内容
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo + "\n" + serverPort;
    }

}
