package com.ecommerce.ecommerce.modules.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPurchaseConfirmationEmail(String receiver, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

//        System.out.println("Purchase successful email send!");
    }

    public void sendEmailWithAttachment(String receiver, String subject, String body, String attachment) throws MessagingException,
            IOException {

        MimeMessage message = mailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(body, true);

        FileSystemResource file = new FileSystemResource(new File(attachment));

        helper.addAttachment(file.getFilename(), file);

        mailSender.send(message);

    }

}
