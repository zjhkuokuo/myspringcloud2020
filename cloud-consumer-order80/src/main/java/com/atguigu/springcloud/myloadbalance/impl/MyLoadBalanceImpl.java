package com.atguigu.springcloud.myloadbalance.impl;

import com.atguigu.springcloud.myloadbalance.MyLoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2020/12/23 20:03
 */
@Component
public class MyLoadBalanceImpl implements MyLoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //CAS：匹配并交换，轮询
    private final int getAndIncrement(){
        int current;    //当前访问集群服务的次数
        int next;       //下次访问集群服务的次数
        do{
            //获取当前访问的次数
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
            System.out.println("当前访问次数，第next:" + next + "次");
        return next;
    }

    //负载均衡算法：rest接口第几册请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口技术从1开始
    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
