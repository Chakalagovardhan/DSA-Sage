package com.sage.Dsa_sage_backend_producer.repo;





import com.sage.Dsa_sage_backend_producer.entites.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuestionRepo extends MongoRepository<Questions,String> {

    Optional<Questions> findById(String id);
}