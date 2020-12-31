package com.ltl.matrix.rocketmq.beans;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RocketMQChannelManager {

    @Output("output1")
    MessageChannel output1();

    @Output("output2")
    MessageChannel output2();
    
    @Input("input1")
    SubscribableChannel input1();

    @Input("input2")
    SubscribableChannel input2();

    @Input("input3")
    SubscribableChannel input3();
}
