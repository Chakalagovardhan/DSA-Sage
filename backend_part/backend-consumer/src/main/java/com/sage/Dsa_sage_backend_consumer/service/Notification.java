package com.sage.Dsa_sage_backend_consumer.service;

public interface Notification {

    public void TextEamil(String to,String subject,String body);

    public void EmailWithAttachments(String to,String subject,String body, byte[] attachments);
}
