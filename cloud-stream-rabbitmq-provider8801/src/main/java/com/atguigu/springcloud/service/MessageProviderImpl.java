package com.atguigu.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/21 9:40
 */
@EnableBinding(Source.class)    //绑定为消息的生产者，使用的是Source.class,接收者为Sink.class。注意包不能引错
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;      //引入消息管道

    @Override
    public String sendMessage() {
        String msg = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());       //构建消息并通过管道发送
        System.out.println("**************msg:" + msg);
        return msg;
    }
}
