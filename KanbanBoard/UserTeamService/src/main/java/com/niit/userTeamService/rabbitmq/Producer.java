package com.niit.userTeamService.rabbitmq;

import lombok.Data;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    public void sendMessageToRabbitMq(TeamDTO teamDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"team_routing",teamDTO);
    }
}
