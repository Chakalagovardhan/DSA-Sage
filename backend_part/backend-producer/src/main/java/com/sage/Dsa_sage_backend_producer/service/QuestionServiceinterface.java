package com.sage.Dsa_sage_backend_producer.service;

import com.sage.Dsa_sage_backend_producer.entites.Questions;
import com.sage.Dsa_sage_backend_producer.entites.Response;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface QuestionServiceinterface {

    public Questions fetchQuestionById();

    public Map<String,String> stringMaker(Response response);

    public boolean queuePusher(Map<String,String> data) throws ExecutionException, InterruptedException, TimeoutException;
}
