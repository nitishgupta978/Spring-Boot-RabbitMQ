package com.solutionpoint.rabbitmq.consumer;

import com.solutionpoint.rabbitmq.config.MessageConfig;
import com.solutionpoint.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageWithQueue(OrderStatus orderStatus){
        System.out.println("Message received successfully from Queue: "+orderStatus);
    }
}
