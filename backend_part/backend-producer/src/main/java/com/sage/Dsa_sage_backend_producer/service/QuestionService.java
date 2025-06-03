package com.sage.Dsa_sage_backend_producer.service;


import com.sage.Dsa_sage_backend_producer.configurations.KafkaConfigurations;
import com.sage.Dsa_sage_backend_producer.entites.Questions;
import com.sage.Dsa_sage_backend_producer.entites.Response;
import com.sage.Dsa_sage_backend_producer.exceptions.AllExceptions;
import com.sage.Dsa_sage_backend_producer.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class QuestionService implements QuestionServiceinterface {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    private KafkaTemplate<String,Map<String,String>> kafkaTemplate;

    @Override
    public Questions fetchQuestionById() {
        String id="6836ce124fdf46ba41372419";
        Optional<Questions> questions = questionRepo.findById(id);
        return questions.orElse(null);

    }

    @Override
    public Map<String,String> stringMaker(Response response) {

        HashMap<String,String> output = new HashMap<>();

        StringBuilder chat= new StringBuilder("Give the dsa roadmap fro the following data where i have given my ocnfidence level according " +
                " to topic and the level using that parameters please genrate a road-map for 30 days daily task dont give study reources" +
                "the number side of the topic is that the confidence level of the student ratting himself from 1-5 range if the lower the number lower his" +
                "confidence level so lower confidence give some more time in roadmap dont give extra things direct roadmap ");
        String email=response.getEmail();
        Map<String,Integer> result=response.getResponse();
        for(Map.Entry<String,Integer> topic: result.entrySet())
        {
            chat.append(topic.getKey()).append(":").append(topic.getValue()).append(",");
        }
        output.put(email,chat.toString());
        return output;
    }

    @Override
    public boolean queuePusher(Map<String, String> data)  {
       try{
           SendResult<String, Map<String, String>> result =
                   kafkaTemplate.send(KafkaConfigurations.topic, data).get(5, TimeUnit.SECONDS);
           return result !=null && result.getRecordMetadata() !=null;
       }catch (Exception ex)
       {
           throw new AllExceptions.kafkaPushException("Recording the response is failed");
       }


    }




}
