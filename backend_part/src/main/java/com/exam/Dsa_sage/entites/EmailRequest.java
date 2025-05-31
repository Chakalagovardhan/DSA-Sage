package com.exam.Dsa_sage.entites;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmailRequest {

    private String to;
    private String subject;
    private String body;
    private String file;
}
