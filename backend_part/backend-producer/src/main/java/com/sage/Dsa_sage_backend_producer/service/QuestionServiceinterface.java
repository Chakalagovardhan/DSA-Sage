package com.sage.Dsa_sage_backend_producer.service;

import com.sage.Dsa_sage_backend_producer.entites.Questions;
import com.sage.Dsa_sage_backend_producer.entites.UserResponse;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface QuestionServiceinterface {

    public Questions fetchQuestionById();

    public boolean queuePusher(UserResponse response) throws ExecutionException, InterruptedException, TimeoutException;
}
