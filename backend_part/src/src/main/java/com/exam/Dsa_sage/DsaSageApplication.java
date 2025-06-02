package com.exam.Dsa_sage;

import com.exam.Dsa_sage.entites.Response;
import com.exam.Dsa_sage.service.ChatModel;
import com.exam.Dsa_sage.service.NotificationSenderservice;
import com.exam.Dsa_sage.service.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;



@SpringBootApplication
public class DsaSageApplication implements CommandLineRunner {

	@Autowired
	public Questions questionsService;

	@Autowired
	private ChatModel chatModel;

	@Autowired
	private NotificationSenderservice notificationSenderservice;



	public static void main(String[] args) {
		SpringApplication.run(DsaSageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		com.exam.Dsa_sage.entites.Questions doc = questionsService.getQuestionsbyId();

//		if (doc != null) {
//			System.out.println("question"+doc);
//		} else {
//			System.out.println("nothing");
//		}
		Map<String,Integer> values = new HashMap<>();
		values.put("Arrays",2);
		values.put("String",3);
		values.put("Trees",1);
		values.put("Graphs",2);
		values.put("DP",1);
		Response reposne = new Response();
		reposne.setEmail("sgovardhan300@gmail.com");
		reposne.setResponse(values);

		String convertedString=chatModel.StringMaker(reposne);
		String reponseFromChatgpt=chatModel.chatModelRepsonse(convertedString);
		byte[] pdfinMemory = chatModel.generatePdfInMemory(reponseFromChatgpt);
		notificationSenderservice.sendMailWithAttachments(reposne.getEmail(),"Specalized dsa road map","Find the attachemnts"
		,pdfinMemory);
		System.out.println("pdf genaarted and sent sucesfully");




	}
}
