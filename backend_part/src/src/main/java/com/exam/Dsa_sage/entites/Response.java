package com.exam.Dsa_sage.entites;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Response {
    private String email;

    private Map<String, Integer> response;
}
