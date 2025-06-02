package com.exam.Dsa_sage.service;


import org.springframework.stereotype.Service;

@Service
public interface NotificationSenderservice {


    public void sendMail(String to,String subject,String body);

    public void sendMailWithAttachments(String to,String subject,String body ,byte[] bytearray);

}
