package com.sage.Dsa_sage_backend_consumer.service;


import com.sage.Dsa_sage_backend_consumer.entites.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalStageReport {

    @Autowired
    private Helpers helpers;

    @Autowired
    private Notification notification;

    private final String subject="";
    private final String body="";

    public void studentReportMaker(UserResponse response)
    {
        try{
            String promptString = helpers.StringMaker(response.getResponse());
            String chatReponse = helpers.chatResponse(promptString);
            byte[] pdfOutput = helpers.generatePdf(chatReponse);
            notification.EmailWithAttachments(response.getEmail(),subject,body,pdfOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
