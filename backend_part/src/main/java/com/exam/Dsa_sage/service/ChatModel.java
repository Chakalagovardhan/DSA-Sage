package com.exam.Dsa_sage.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatModel {

    private final ChatClient.Builder builder;

    public ChatModel(ChatClient.Builder builder) {
        this.builder = builder;
    }

    public void chatModelRepsonse(String message)
    {
        ChatClient client = builder.build();
        String response =client.prompt(message).call().content();
        System.out.println(response);
    }
}
