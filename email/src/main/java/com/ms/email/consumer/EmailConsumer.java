package com.ms.email.consumer;

import com.ms.email.dto.EmailDTO;
import com.ms.email.service.EmailService;
import com.ms.email.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = {"${broker.queue.email.name}"})
    public void receive(@Payload EmailDTO emailDTO){
        var email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        emailService.sendEmail(email);

    }
}
