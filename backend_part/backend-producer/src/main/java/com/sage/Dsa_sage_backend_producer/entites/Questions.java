package com.sage.Dsa_sage_backend_producer.entites;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

    @Id
    private String id;

    @Field("questions")
    private List<String> questions;

    @Field("section-4")
    private List<String> timedetails;


    @Override
    public String toString() {
        return "Questions{" +
                "id='" + id + '\'' +
                ", questions=" + questions +
                ", timedetails=" + timedetails +
                '}';
    }

}
