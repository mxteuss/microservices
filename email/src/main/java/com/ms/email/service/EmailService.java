package com.ms.email.service;

import com.ms.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import com.ms.email.model.Email;
import com.ms.email.model.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class  EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email){
    try{
        email.setSendTime(LocalDateTime.now());
        email.setEmailFrom(emailFrom);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getEmailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getText());
        emailSender.send(mailMessage);

        email.setStatus(Status.DELIVERED);
    }
    catch (MailException e){
        email.setStatus(Status.ERROR);
        e.printStackTrace();
    }
    return emailRepository.save(email);
    }
}
