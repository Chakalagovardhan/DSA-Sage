package com.sage.Dsa_sage_backend_producer.controller;


import com.sage.Dsa_sage_backend_producer.entites.Questions;
import com.sage.Dsa_sage_backend_producer.entites.Response;
import com.sage.Dsa_sage_backend_producer.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/reponse")
public class AllContoller {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/dsaquestions")
    public ResponseEntity<?> getQuestionsFromDb()
    {
        Questions response = questionService.fetchQuestionById();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/send-response")
    public ResponseEntity<?> handelResponse(@RequestBody Response response)  {
        Map<String, String> output = questionService.stringMaker(response);
            boolean result = questionService.queuePusher(output);
            return new ResponseEntity<>("Response recorded sucessfully",HttpStatus.OK);


    }
}
