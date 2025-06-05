package com.sage.Dsa_sage_backend_producer.service;


import com.sage.Dsa_sage_backend_producer.configurations.kafkaConstants;
import com.sage.Dsa_sage_backend_producer.entites.Questions;
import com.sage.Dsa_sage_backend_producer.entites.UserResponse;
import com.sage.Dsa_sage_backend_producer.exceptions.AllExceptions;
import com.sage.Dsa_sage_backend_producer.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class QuestionService implements QuestionServiceinterface {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    private KafkaTemplate<String, UserResponse> kafkaTemplate;

    @Override
    public Questions fetchQuestionById() {
        String id="6836ce124fdf46ba41372419";
        Optional<Questions> questions = questionRepo.findById(id);
        return questions.orElse(null);

    }



    @Override
    public boolean queuePusher(UserResponse response)  {
       try{
           SendResult<String, UserResponse> result =
                   kafkaTemplate.send(kafkaConstants.topic, response).get(5, TimeUnit.SECONDS);
           return result !=null && result.getRecordMetadata() !=null;
       }catch (Exception ex)
       {
           throw new AllExceptions.kafkaPushException("Recording the response is failed");
       }


    }




}
