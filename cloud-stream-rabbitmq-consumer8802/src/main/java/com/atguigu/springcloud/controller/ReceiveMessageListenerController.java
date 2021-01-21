package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/21 10:27
 */
@Component
@EnableBinding(Sink.class)      //绑定为消息的接收者
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverport;

    @StreamListener(Sink.INPUT)
    public void getMessage(Message<String> message){  //这里的Message泛型类型必须和生产者发送的消息类型一致
        String msg = message.getPayload();
        System.out.println("消费者" + serverport + "接收到消息为：" + msg);
    }

}
