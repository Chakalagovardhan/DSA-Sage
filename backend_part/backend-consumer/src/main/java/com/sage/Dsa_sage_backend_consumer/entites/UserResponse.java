package com.sage.Dsa_sage_backend_consumer.entites;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class UserResponse {

    private String email;

    private Map<String, Integer> response;
}
