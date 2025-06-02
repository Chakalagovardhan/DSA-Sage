package com.exam.Dsa_sage.repo;


import com.exam.Dsa_sage.entites.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuestionRepo extends MongoRepository<Questions,String> {

    Optional<Questions> findById(String id);
}
