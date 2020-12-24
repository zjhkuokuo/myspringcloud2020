package com.atguigu.springcloud.myloadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 *  自定义负载均衡的轮询算法
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/23 19:59
 */
public interface MyLoadBalance {

    //ServiceInstance：可以获取在服务注册中心注册的某个服务集群里的实例。
    ServiceInstance getInstance(List<ServiceInstance> serviceInstances);
}
