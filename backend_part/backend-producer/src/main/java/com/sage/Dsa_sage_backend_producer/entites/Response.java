package com.sage.Dsa_sage_backend_producer.entites;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Response {
    private String email;

    private Map<String, Integer> response;
}