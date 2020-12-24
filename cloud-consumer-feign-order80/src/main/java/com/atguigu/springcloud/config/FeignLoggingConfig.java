package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/24 16:39
 */
@Configuration
public class FeignLoggingConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;   //设置日志打印的级别
    }
}
