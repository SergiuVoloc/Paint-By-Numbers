package com.ecommerce.ecommerce.modules.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private JavaMailSender mailSender;

    public void sendPurchaseConfirmationEmail(String receiver, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("voloc.sergiu.i7c@student.ucv.ro");
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(body);
//        message.setBcc("voloc.sergiu.i7c@student.ucv.ro");

        mailSender.send(message);

        System.out.println("Mail send!");

    }
}
