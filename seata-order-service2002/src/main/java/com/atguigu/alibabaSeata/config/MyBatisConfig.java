package com.atguigu.alibabaSeata.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/2/7 23:36
 */
@Configuration
@MapperScan({"com.atguigu.alibabaSeata.dao"})
public class MyBatisConfig {
}
