package com.exam.Dsa_sage.service;


import com.exam.Dsa_sage.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class Questions {


    @Autowired
    private QuestionRepo questionRepo;

    public com.exam.Dsa_sage.entites.Questions getQuestionsbyId()
    {
        String id="6836ce124fdf46ba41372419";
        Optional<com.exam.Dsa_sage.entites.Questions> questions = questionRepo.findById(id);
            return questions.orElse(null);
    }
}
