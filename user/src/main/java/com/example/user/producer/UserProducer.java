package com.example.user.producer;

import com.example.user.domain.User;
import com.example.user.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value ="${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
       var emailDTO = new EmailDTO();
       emailDTO.setUserId(user.getUserId());
       emailDTO.setEmailTo(user.getEmail());
       emailDTO.setSubject("Cadastro realizado com sucesso!");
       emailDTO.setText(user.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite a plataforma");


       rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
