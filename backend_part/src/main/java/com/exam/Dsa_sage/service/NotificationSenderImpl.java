package com.exam.Dsa_sage.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class NotificationSenderImpl implements NotificationSenderservice {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendMail(String to, String subject, String body) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
        }catch (Exception ex)
        {
            System.out.println("Exception in sending the mail");
        }
    }

    @Override
    public void sendMailWithAttachments(String to, String subject, String body, String filePath) {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            mimeMessageHelper.addAttachment("DSA Road map",file);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            System.out.println("Excpetion in sending the mail");
        }
    }
}
