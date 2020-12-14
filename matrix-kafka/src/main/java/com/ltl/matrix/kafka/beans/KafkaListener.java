package com.ltl.matrix.kafka.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(value = KafkaChannelManager.class)
public class KafkaListener {

    /**
     * 从缺省通道接收消息
     * @param message
     */
    @StreamListener(KafkaChannelManager.ES_DEFAULT_INPUT)
    public void receive(Message<String> message){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(new Date())+"------start--------安全用电默认消息：" + message);
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(new Date())+"------end--------安全用电默认消息");
    }

    /**
     * 从告警通道接收消息
     * @param message
     */
    @StreamListener(KafkaChannelManager.ES_ALARM_INPUT)
    public void receiveAlarm(Message<String> message){
        System.out.println("订阅告警消息：" + message);
    }
}
