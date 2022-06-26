package com.solutionpoint.rabbitmq.publish;

import com.solutionpoint.rabbitmq.config.MessageConfig;
import com.solutionpoint.rabbitmq.dto.Order;
import com.solutionpoint.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/{hotelName}")
    public String bookOrder(@RequestBody  Order order, @PathVariable String hotelName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus= new OrderStatus(order,"Process","Order place SuccessFully in "+hotelName);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,orderStatus);
        return "Your Order successfully Order By !! "+hotelName;
    }
}
