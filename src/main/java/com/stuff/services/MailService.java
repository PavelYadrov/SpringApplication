package com.stuff.services;

import com.stuff.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private Message message;

    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailTo,String subject,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("your_email");
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);

    }

    public Message getMessage() {
        return message;
    }
}
