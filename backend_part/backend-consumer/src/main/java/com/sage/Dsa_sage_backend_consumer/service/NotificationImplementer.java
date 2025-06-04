package com.sage.Dsa_sage_backend_consumer.service;

import com.sage.Dsa_sage_backend_consumer.exceptions.AllExceptions;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationImplementer implements Notification{


    @Autowired
    private JavaMailSender mailsender;

    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private RestClient.Builder builder;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void TextEamil(String to, String subject, String body) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setFrom(sender);
            mailMessage.setSubject(subject);
            mailMessage.setText(body);
            mailsender.send(mailMessage);
        }catch (Exception ex)
        {
            throw new AllExceptions.MailSendingError("unable to the send the mail to "+to);
        }
    }

    @Override
    public void EmailWithAttachments(String to, String subject, String body, byte[] attachments) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);
            ByteArrayResource resources = new ByteArrayResource(attachments);
            mimeMessageHelper.addAttachment("Customized DSA study planner.pdf",resources);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new AllExceptions.MailSendingError("unable to the send the mail to "+to);
        }
    }
}
