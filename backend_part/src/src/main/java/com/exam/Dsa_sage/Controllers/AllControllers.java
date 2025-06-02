//package com.exam.Dsa_sage.Controllers;
//
//
//import com.exam.Dsa_sage.entites.EmailRequest;
//import com.exam.Dsa_sage.service.ChatModel;
//import com.exam.Dsa_sage.service.NotificationSenderservice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AllControllers {
//
//
//    @Autowired
//    private NotificationSenderservice notificationSenderservice;
//
//    @Autowired
//    private ChatModel chatModel;
//
//    @PostMapping("/simplemail")
//    public ResponseEntity<?> sendMail(@RequestBody EmailRequest emailRequest)
//    {
//        notificationSenderservice.sendMail(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getBody());
//        return new ResponseEntity<>("mail sent sucessfully ", HttpStatus.OK);
//    }
//
//    @PostMapping("/mailwithattachments")
//    public ResponseEntity<?> sendMailWithAttachments(@RequestBody EmailRequest emailRequest)
//    {
//        notificationSenderservice.sendMailWithAttachments(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getBody(),emailRequest.getFile());
//        return new ResponseEntity<>("mail sent sucessfully with attchments", HttpStatus.OK);
//    }
//
//    @GetMapping("/getresponse")
//    public ResponseEntity<?> getmodelchat()
//    {
//        chatModel.chatModelRepsonse();
//        return new ResponseEntity<>("message created sucessfully",HttpStatus.OK);
//
//    }
//
//
//}
